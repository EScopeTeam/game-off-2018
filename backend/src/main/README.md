## Instructions to create & run the Docker image with PostgreSQL database already created and populated.

Execute the following command in this same directory:

`docker build -f Dockerfile -t postgre/bugs .`

To run the created image:

`docker run -p 5432:5432 -P --name bichos_db postgre/bichos`

user: bichos

password: bichitos
