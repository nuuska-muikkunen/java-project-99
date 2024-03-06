DEFAULT_GOAL := build-run

setup:
	./gradle wrapper --gradle-version 8.6
	./gradlew build

app:
	./gradlew bootRun --args='--spring.profiles.active=production'

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew installDist

run-dist:
	./build/install/app/bin/app

run:
	./gradlew bootRun

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release

.PHONY:
	build
