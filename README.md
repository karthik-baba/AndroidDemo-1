# AndroidDemo-1

## Implemenation:
*Completed the Mobile Test Challenge with the following enhancements:*
 - Capturing Screenshots using Spoon Open-Source Framework(http://square.github.io/spoon/)
 - Added additional tests to verify negative scenarios and UI elements properties
 - Tests ran locally in 3 devices with 3 different OS[Marshmallow, Nougat, Oreo] - Attaching Spoon Report
 - Tests ran with CircleCI in 1 device running Lollipop OS - Shared CircleCI Build Summary link
 
*In this process I have learnt the following:*
- Gradle
- Espresso
- Android Studio usage
- Instrumentation Testing Robots [https://academy.realm.io/posts/kau-jake-wharton-testing-robots/]
- CircleCI 2.0 - Builds
- Creation of config.yml files for CircleCI
- Docker
- Running CircleCI images in Docker
- Configuration of Emulators in Docker Images
- Spoon framework
- Falconspoon - For creating good quality screenshots
- Android Permissions for versions >= Marshmallow

## Best Practices Implemented
- Meaningful methods names with proper comments
- Modularity and Reusablity
- Captured device logs using Spoon framework
- Proofs for test case execution with screenshots
- Easy Maintenance [if there is any change to UI Elements]

## Modification of Source

I have overridden the location permissions and external storage permissions for the testing purpose.

## Artifacts

- CircleCI Build  :   https://circleci.com/gh/karthik-baba/AndroidDemo-1/108
- Spoon Report  :   https://108-129112918-gh.circle-artifacts.com/0/home/circleci/Android-Demo-1/app/build/custom-report-dir/debug/index.html
