import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    // Generic
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlinStdLibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    private val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    private val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Android UI
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha02"
    private val lifecycleCommonJava = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycleKtx}"
    private val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    private val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    private val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    private val activityCompose = "androidx.activity:activity-compose:1.3.0-rc01"

    private val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    private val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    private val composeUiTooling = "androidx.compose.ui:ui-tooling:1.0.0-beta09"
    private val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navCompose}"

    private val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    private val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    private val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    private val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"

    private val okHttpUrlConnection = "com.squareup.okhttp3:okhttp-urlconnection:4.4.1"
    private val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private val retrofit2Mock = "com.squareup.retrofit2:retrofit-mock:2.6.0"

    private val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    private val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    private val gson = "com.google.code.gson:gson:${Versions.gson}"

    private val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"

    private val material = "com.google.android.material:material:${Versions.material}"
    private val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    // Accompanist
    private val accompanistSwipeRefresh = "com.google.accompanist:accompanist-swiperefresh:${Versions.accompanistVersion}"
    private val accompanistUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanistVersion}"

    //  private val coil = "com.google.accompanist:accompanist-coil:${Versions.accompanistCoil}"
    private val coilCompose = "io.coil-kt:coil-compose:1.3.0"

    private val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    private val composeMaterialDialogsCore = "io.github.vanpra.compose-material-dialogs:core:0.4.3"
    private val composeMaterialDialogsDatetime = "io.github.vanpra.compose-material-dialogs:datetime:0.4.3"

    // Hilt
    private val hiltCore = "com.google.dagger:hilt-core:${Versions.hilt}"
    private val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    private val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    private val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    private val hiltJetpackCompiler = "androidx.hilt:hilt-compiler:${Versions.jetpackHiltVersion}"
    private val hiltNavFragment = "androidx.hilt:hilt-navigation-fragment:${Versions.jetpackHiltVersion}"
    private val hiltNavigation = "androidx.hilt:hilt-navigation:${Versions.hiltNavigation}"
    private val hiltComposeNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavVersion}"

    // Navigation
    private val navFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    private val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    // Lifecycle
    private val lifecycleViewModelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelComposeVersion}"
    private val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_lifecycle_viewmodel}"
//    private val lifecycleViewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"


    // Test
    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val testComposeUi = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(hiltAndroid)
        add(hiltNavigation)
        add(hiltComposeNavigation)
        add(hiltNavFragment)
        add(coreKtx)
        add(appcompat)
        add(coroutinesCore)
        add(coroutinesAndroid)
        add(lifecycleCommonJava)
        add(lifecycleViewModel)
        add(timber)
        add(loggingInterceptor)
        add(activityKtx)
        add(material)
        add(lottie)
        add(composeUi)
        add(composeMaterial)
        add(composeUiTooling)
        add(navigationCompose)
        add(activityCompose)
        add(lifecycleViewModelCompose)
        add(hiltLifecycleViewModel)
        add(okHttp)
        add(okHttpUrlConnection)
        add(retrofit2)
        add(retrofit2ConverterGson)
        add(gson)
        add(retrofit2Mock)
        add(roomRuntime)
        add(roomKtx)
        add(datastore)
        add(lifecycleRuntime)
        add(coilCompose)
        add(accompanistUiController)
        add(accompanistSwipeRefresh)
        add(materialIconsExtended)
        add(composeMaterialDialogsCore)
        add(composeMaterialDialogsDatetime)
    }

    val libLibraries = arrayListOf<String>().apply {
        add(kotlinStdLibJdk7)
        add(hiltCore)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(hiltCompiler)
        add(roomCompiler)
        add(hiltAndroidCompiler)
        add(hiltJetpackCompiler)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
        add(testComposeUi)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
        add(mockWebServer)
    }

}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}