# Use the official Python image as a base image
FROM python:3.8

# Set the working directory to /app
WORKDIR /app

# Copy just the requirements file to leverage Docker cache
COPY requirements.txt .

# Install any needed packages specified in requirements.txt
RUN pip install -r requirements.txt

# Copy the rest of the application code
COPY . .

# Expose the port that your app will run on
EXPOSE 8080

# Define the command to run your application using Gunicorn
CMD ["gunicorn", "--bind", "0.0.0.0:8080", "app.run:app"]
