FROM mysql:8.0.21
CMD ["--max_connections=10000"]
ADD database/ /docker-entrypoint-initdb.d
EXPOSE 3306