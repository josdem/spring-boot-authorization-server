Spring Boot Authorization Server
----------------------------

[![GitHub](https://github.com/josdem/spring-boot-authorization-server/actions/workflows/main.yml/badge.svg)](https://github.com/josdem/spring-boot-authorization-server/actions)
[![Jenkins](https://ci.josdem.io/job/spring-boot-authorization-server/badge/icon)](https://ci.josdem.io/job/spring-boot-authorization-server/)

This project shows how to implement [Oauth 2.0](https://oauth.net/2/) with [Spring Boot Security](https://docs.spring.io/spring-security/reference/index.html) project

#### To run the project
```bash
gradle bootRun
```

#### To run the project in production
```bash
gradle bootRun -Dspring.profiles.active=prod
```

#### To run tests
```bash
gradle test
```

#### To run a single test
```bash
gradle test --tests ${testName}
```

Where: `${testName}` is the name of the test you want to run

#### To issue a token
```bash
curl -X POST https://auth.josdem.io/oauth2/token -u "client:secret" -d "grant_type=client_credentials" -d "scope=write"
```

And you should get a response like

```bash
{"access_token":"eyJraWQiOiI1YTMwN2I1My03NDM2LTQ4NWUtOTVkZC1mZDkxZjg1YTZmNDkiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJjbGllbnQiLCJhdWQiOiJjbGllbnQiLCJuYmYiOjE2OTM2MDA1MDUsInNjb3BlIjpbIndyaXRlIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6OTAwMC8iLCJleHAiOjE2OTM2MDA4MDUsImlhdCI6MTY5MzYwMDUwNX0.UsEfCzgaFaix2vxcw4sqobs2ChkvCI-DP1vP7t9uHQXEEhgyhOm6gWEci6PdZa0yoqUW6Yg25YZ03m5rzcolL6CADWSP2tJ4WvPNU4wvRosNTKU94j3Scbbp1M8SBpFQsOdxApMN7W11EULeafIBad_XiuQvrEJHbxowdyDimVSPZQwlh1mamszuU3hVBnhJF_0YceBDtttSfkIvreqq6d7BuPVCJWOjdHwXTGRpi5V8AUqzoJIiAR8-3Z4SrxKJUah5GOgOm4OqZyTO31paE50wphKPq9VT0_cWRM36B7cve6_hXG5qhEaYLu3G-vzk-mBQgECkk-YqEEy_dCmi5Q","scope":"write","token_type":"Bearer","expires_in":299}
```

#### Read this as reference

* http://josdem.io/techtalk/spring/spring_webflux_security/

