# springboot-crud-app

Before you start it's required that you have installed:

- helm
- kubernetes
- kubectl
- docker

Build app like so (bump the docker image version):

> docker build . -t sIuv/plinko:latest -t sIuv/plinko:0.0.1

Update the docker image version in the ./helm/app/values.yaml

Update the appVersion in ./helm/app/Chart.yaml

Deploy the kubernetes builds:

> helm upgrade plinko-app ./helm/app

> helm upgrade postgres ./helm/postgres
