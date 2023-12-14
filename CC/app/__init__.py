from flask import Flask

def create_app():
  app = Flask(__name__)

  from routes import routes as routes_blueprint
  app.register_blueprint(routes_blueprint)
  return app