package com.kultura.app.view.navigation

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.kultura.app.data.remote.ApiConfig
import com.kultura.app.data.response.HasilItem
import com.kultura.app.databinding.FragmentScanBinding
import com.kultura.app.view.camera.getImageUri
import com.kultura.app.view.camera.reduceFileImage
import com.kultura.app.view.camera.uriToFile
import com.kultura.app.view.detail.DetailTopengActivity
import com.kultura.app.view.guide.GuideActivity
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException

class ScanFragment : Fragment() {
    private lateinit var binding: FragmentScanBinding
    private lateinit var id: String

    private var currentImageUri: Uri? = null


    private val launcherGallery =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null) {
                currentImageUri = uri
                showImage()
            } else {
                showToast("No Media Selected")
            }
        }

    private val launcherIntentCamera =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                showImage()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.galleryButton.setOnClickListener {startGallery()}
        binding.cameraButton.setOnClickListener {startCamera()}
        binding.scanButton.setOnClickListener {
            val foto = binding.ivScanPhoto.drawable
            if (foto != null) {
                uploadImage()
            }else {
                showToast("Silahkan Masukkan Foto untuk di scan")
            }
        }

        binding.tvGuideScan.setOnClickListener {
            val intentGuide = Intent(requireContext(), GuideActivity::class.java)
            startActivity(intentGuide)
        }

    }

    private fun startGallery() {
        launcherGallery.launch("image/*")
    }

    private fun startCamera() {
        currentImageUri = getImageUri(requireContext())
        launcherIntentCamera.launch(currentImageUri)
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.ivScanPhoto.setImageURI(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun uploadImage() {
        currentImageUri?.let { uri ->
            val imageFile = uriToFile(uri, requireContext()).reduceFileImage()
//            Log.d("Image File", "showImage: ${imageFile.path}")
            showLoading(true)

            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val multipartBody = MultipartBody.Part.createFormData(
                "file",
                imageFile.name,
                requestImageFile
            )

            lifecycleScope.launch {
                try {
                    val apiService = ApiConfig.getApiService()
                    val successResponse = apiService.uploadImage(multipartBody)
                    val hasil : List<HasilItem> = successResponse.hasil
                    id = hasil[0].id.toString()
                    val intentDetail = Intent(requireContext(), DetailTopengActivity::class.java)
                    intentDetail.putExtra(DetailTopengActivity.EXTRA_ID, id)
                    startActivity(intentDetail)
                    showLoading(false)
                } catch (e: HttpException) {
                    val errorBody = e.response()?.errorBody()?.string()
                    showToast(errorBody!!)
                    showLoading(false)
                }
            }

        }
    }

    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }

}