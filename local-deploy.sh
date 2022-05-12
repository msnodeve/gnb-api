docker build -t gnb-api .

docker stop gnb-api || ture
docker rm gnb-api || true
docker run -d -p 8080:8080 --name gnb-api gnb-api