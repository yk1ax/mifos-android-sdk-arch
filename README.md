# Mifos Android Architecture
This is architecture repository for mifos-android-sdk

## Architecture
1. The core module in the SDK contains all the API requests written.
2. The entry point to the library is the [Mifos SDK](https://github.com/openMF/mifos-android-sdk-arch/blob/master/core/src/main/java/org/mifos/core/MifosSdk.kt).
3. Builder pattern is used to facilitate creation an object of library. This object talks to the DataManager Layer and exposes all the APIs.
4. The library is built using the MVVM architecture with modifications.
