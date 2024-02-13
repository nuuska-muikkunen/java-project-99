DEFAULT_GOAL := build-run

setup:
	gradle wrapper --gradle-version 8.6

app:
	./app/gradlew -p app bootRun --args='--spring.profiles.active=dev'

clean:
	./app/gradlew -p app clean

build:
	./app/gradlew -p app clean build

install:
	./app/gradlew -p app clean installDist

run-dist:
	./app/build/install/app/bin/app

run:
	./app/gradlew -p app run

test:
	./app/gradlew -p app test

report:
	./app/gradlew -p app jacocoTestReport

lint:
	./app/gradlew -p app checkstyleMain

check-deps:
	./app/gradlew -p app dependencyUpdates -Drevision=release

build-run:
	build run

.PHONY:
	build
