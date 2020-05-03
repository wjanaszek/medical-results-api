# Getting started
Follow the steps below.
## Generate ENV file
Copy `.env-sample` file. Rename it to `.env`. Then fill it with proper database connection values.
## Start API with database in a container
`docker-compose up build` - this will run API exported at port `8080`, connected to a database.
## Start database in a container
`docker-compose up mrs-postgres` - this will run database exported at port `5432`.
## Shutdown
`docker rm $(docker ps -a -q) -f` or `docker system prune --volumes`.
