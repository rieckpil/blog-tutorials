from diagrams import Diagram
from diagrams.onprem.client import Users
from diagrams.onprem.database import PostgreSQL
from diagrams.programming.framework import Spring

graph_attr = {
  "fontsize": "20",
  "bgcolor": "white"  # transparent
}

with Diagram("", direction="LR", graph_attr=graph_attr, outformat="png", filename="architecture"):
  users = Users("Users")
  backend = Spring("Application")
  database = PostgreSQL("PostgreSQL database")

  backend >> database
  users >> backend
