# Apollo-Android
This project is intended to demo the use of Apollo Android to handle GraphQL data

## Followed Procedure For Project Setup
### Dependency
#### Project Level: build.gradle
- classpath 'com.apollographql.apollo:apollo-gradle-plugin:1.0.1'

#### App Level: build.gradle
- implementation 'com.apollographql.apollo:apollo-runtime:1.0.1'
- implementation "com.apollographql.apollo:apollo-android-support:1.0.0"
- implementation 'com.squareup.okhttp3:okhttp:3.12.2'

### Plugin (app Level build.gradle)
- apply plugin: 'com.apollographql.android'

### Project Structure
- Create a directory named graphql along the path: src/main/{graphql}
- This directory should be at the same level as java: src/main/java
- In the graphql directory, create a directory with the same name as that inside the src/main/java
- Inside the directory created above, create a .graphql file named api in which your query and mutations shall be written.
- Download the schema using the provided endpoint.

### Downloading the schema
- cd into the created directory and run command: apollo schema:download --endpoint={put endpoint here} schema.json

### Note: 
- Ensure that apollo is installed on your machine by running a command: npm install -g apollo 
- The downloaded schema will be named schema.json

## How To Test The Application
- clone the repository at: https://github.com/moseskamira/Apollo-Android.git
- Open in project in Android studio
- Run the application using AVD or a real connected device




