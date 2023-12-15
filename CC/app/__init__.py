from flask import Flask
from app import routes

def create_app():
    app = Flask(__name__)
    app.register_blueprint(routes.routes)
    return app