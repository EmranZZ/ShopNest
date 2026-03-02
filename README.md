# ShopNest 🛍️

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-4285F4.svg)](https://developer.android.com/jetpack/compose)
[![Firebase](https://img.shields.io/badge/Backend-Firebase-orange.svg)](https://firebase.google.com/)
[![Material Design 3](https://img.shields.io/badge/Design-Material%203-purple.svg)](https://m3.material.io/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A modern, feature-rich Android e-commerce application built with **Jetpack Compose** and **Firebase**, delivering a seamless shopping experience with elegant UI/UX design and cutting-edge Android development practices.

> **⚠️ Work in Progress**: This project is currently under active development. Features and documentation are being continuously updated. Star ⭐ the repository to follow the progress!

## 📋 Table of Contents

- [Features](#-features)
- [Screenshots](#-screenshots)
- [Tech Stack](#-tech-stack)
- [Architecture](#-architecture)
- [Prerequisites](#-prerequisites)
- [Installation](#-installation)
- [Configuration](#-configuration)
- [Project Structure](#-project-structure)
- [Development](#-development)
- [Building](#-building)
- [Contributing](#-contributing)
- [Roadmap](#-roadmap)
- [License](#-license)
- [Contact](#-contact)

## ✨ Features

### 🚀 Current Features

#### Authentication & User Management
- 🔐 Secure user registration and login system
- 🔑 Firebase Authentication integration
- 🛡️ Secure session management and persistence
- 👤 User profile support

#### Shopping Experience
- 🛒 **Product Catalog**: Browse through a wide range of products
- 📱 **Product Details**: Comprehensive product information view
- 🛍️ **Shopping Cart**: Add, remove, and manage cart items
- 🏷️ **Category Filtering**: Filter products by categories
- 🎯 **Smart Navigation**: Intuitive navigation between screens

#### UI/UX Excellence
- 🎨 **Material Design 3**: Modern and beautiful interface
- 📱 **Responsive Design**: Adaptive layouts for all screen sizes
- ✨ **Smooth Animations**: Fluid transitions and interactions
- 🌟 **Custom Splash Screen**: Branded app launch experience
- 🖼️ **Rich Media Support**: High-quality image rendering with caching

#### Technical Features
- ⚡ **Optimized Performance**: Efficient image loading and caching with Coil
- 🔄 **Reactive UI**: Real-time updates using Kotlin Flows
- 🎯 **MVVM Architecture**: Clean, maintainable, and scalable code structure
- 📊 **State Management**: Robust state handling with ViewModels

### 🔮 Upcoming Features

#### Enhanced Shopping
- 💳 **Payment Integration**: Multiple payment gateway support
- 📦 **Order Management**: Track and manage orders
- 📜 **Order History**: View past purchases and order details
- ❤️ **Wishlist**: Save favorite products for later
- 🔍 **Advanced Search**: Smart search with filters and sorting

#### User Experience
- ⭐ **Ratings & Reviews**: Product review system
- 💬 **Push Notifications**: Real-time updates and offers
- 🎁 **Promotional System**: Coupons, discounts, and deals
- 📍 **Address Management**: Multiple delivery addresses
- 🌐 **Multi-language Support**: Localization for global audience

#### Advanced Features
- 🎯 **Personalized Recommendations**: AI-powered product suggestions
- 📊 **Analytics Dashboard**: User activity insights
- 🌙 **Dark Mode**: Complete dark theme support
- 💾 **Offline Mode**: Browse cached products offline
- 🔐 **Biometric Authentication**: Fingerprint/Face ID login
- 🔔 **Real-time Inventory**: Live stock updates

## 📸 Screenshots

_Screenshots will be added as features are completed._

## 🛠️ Tech Stack

### Core Technologies
- **Language**: [Kotlin](https://kotlinlang.org/) 2.0.21
  - Modern, concise, and safe programming language
  - Full interoperability with Java
  - Coroutines for asynchronous programming
- **Minimum SDK**: 24 (Android 7.0 Nougat)
- **Target SDK**: 36 (Android 15)
- **Compile SDK**: 36
- **Build System**: Gradle 8.13.2 with Kotlin DSL
- **JDK**: Java 11

### Architecture & Android Components
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
  - Modern declarative UI toolkit
  - BOM Version: 2024.09.00
  - Reactive and efficient UI rendering
- **Architecture Pattern**: MVVM (Model-View-ViewModel)
  - Clean separation of concerns
  - Testable and maintainable code structure
- **Navigation**: [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) 2.9.7
  - Type-safe navigation between composables
  - Deep linking support
- **Lifecycle**: AndroidX Lifecycle 2.10.0
  - Lifecycle-aware components
  - ViewModel and StateFlow integration
- **Activity**: AndroidX Activity Compose 1.12.3
- **Core KTX**: AndroidX Core 1.17.0
  - Kotlin extensions for Android APIs

### Backend & Cloud Services
- **Authentication**: [Firebase Authentication](https://firebase.google.com/docs/auth) 24.0.1
  - Email/password authentication
  - Social login support
  - Secure token-based authentication
- **Database**: [Cloud Firestore](https://firebase.google.com/docs/firestore) 26.1.0
  - Real-time NoSQL database
  - Offline data persistence
  - Automatic data synchronization
- **Google Services**: Firebase SDK 4.4.4

### Third-Party Libraries
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/) 2.7.0
  - Kotlin-first image loading library
  - Coroutine-based
  - Memory and disk caching
  - Jetpack Compose native support
- **UI Indicators**: [DotsIndicator](https://github.com/tommybuonomo/dotsindicator) 5.1.0
  - Elegant carousel indicators
  - Material Design 3 compatible
- **Splash Screen**: AndroidX Core SplashScreen 1.2.0
  - Backward-compatible splash screen API
  - Material Design 3 support

### Testing & Quality Assurance
- **Unit Testing**: JUnit 4.13.2
- **Instrumentation Testing**: AndroidX Test 1.3.0
- **UI Testing**: Compose UI Test
- **Espresso**: AndroidX Espresso 3.7.0

### Development Tools & Environment
- **IDE**: Android Studio Iguana or newer
- **Version Control**: Git
- **Dependency Management**: Gradle Version Catalog (TOML)
- **Code Quality**: Kotlin Coding Conventions
- **Build Configuration**: Kotlin DSL (.kts)

## 🏗️ Architecture

ShopNest follows the **MVVM (Model-View-ViewModel)** architectural pattern with clean architecture principles:

```
┌─────────────────────────────────────────────────────┐
│                   Presentation Layer                 │
│  ┌──────────────┐  ┌──────────────┐  ┌───────────┐ │
│  │  Composables │  │   Screens    │  │   Pages   │ │
│  └──────────────┘  └──────────────┘  └───────────┘ │
└────────────────────────┬────────────────────────────┘
                         │
┌────────────────────────┼────────────────────────────┐
│                   ViewModel Layer                    │
│  ┌────────────────────────────────────────────────┐ │
│  │     ViewModels (Business Logic & State)        │ │
│  └────────────────────────────────────────────────┘ │
└────────────────────────┬────────────────────────────┘
                         │
┌────────────────────────┼────────────────────────────┐
│                    Data Layer                        │
│  ┌──────────────┐  ┌──────────────┐  ┌───────────┐ │
│  │    Models    │  │  Repository  │  │  Firebase │ │
│  └──────────────┘  └──────────────┘  └───────────┘ │
└─────────────────────────────────────────────────────┘
```

### Key Principles
- **Separation of Concerns**: Clear separation between UI, business logic, and data
- **Unidirectional Data Flow**: State flows down, events flow up
- **Single Source of Truth**: ViewModels hold UI state
- **Reactive Programming**: Using Kotlin Flows and StateFlow

## 📋 Prerequisites

Before you begin, ensure you have the following installed and configured:

### Required Software
- **Android Studio**: Iguana (2023.2.1) or newer
  - Download from [Android Studio](https://developer.android.com/studio)
  - Includes Android SDK and essential build tools
- **JDK**: Java Development Kit 11 or higher
  - OpenJDK 11 recommended
  - Verify with: `java -version`
- **Android SDK**: 
  - Platform: Android 15 (API Level 36)
  - Build Tools: Latest version
  - SDK Platform-Tools: Latest version
- **Gradle**: 8.0 or higher (bundled with Android Studio)
- **Git**: For version control
  - Verify with: `git --version`

### Required Accounts & Services
- **Firebase Account**: 
  - Create at [Firebase Console](https://console.firebase.google.com/)
  - Required for authentication and database services
- **Google Account**: For Firebase and Google Play Services

### System Requirements
- **Operating System**: 
  - Windows 10/11 (64-bit)
  - macOS 10.14 (Mojave) or higher
  - Linux (64-bit GNU/Linux)
- **RAM**: 8 GB minimum, 16 GB recommended
- **Disk Space**: 4 GB minimum for Android Studio + 8 GB for SDK and emulator
- **Screen Resolution**: 1280 x 800 minimum

### Optional but Recommended
- **Android Device** or **Emulator**:
  - Physical device with USB debugging enabled
  - Or Android Emulator with API Level 24 or higher
- **Postman** or similar API testing tool (for future backend development)
- **Git GUI Client** (GitKraken, SourceTree, or GitHub Desktop)

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/ShopNest.git
cd ShopNest
```

### 2. Open in Android Studio

1. Launch Android Studio
2. Select `File > Open`
3. Navigate to the cloned repository directory
4. Click `OK` to open the project

### 3. Sync Gradle

Android Studio will automatically prompt you to sync Gradle files. Click `Sync Now`.

## ⚙️ Configuration

### Firebase Setup

#### 1. Create a Firebase Project
1. Go to [Firebase Console](https://console.firebase.google.com/)
2. Click **"Add project"** or **"Create a project"**
3. Enter project name: `ShopNest` (or your preferred name)
4. (Optional) Enable Google Analytics for your project
5. Click **"Create project"** and wait for setup to complete

#### 2. Add Android App to Firebase
1. In Firebase Console, click the **Android icon** to add an Android app
2. Register app with the following details:
   - **Android package name**: `com.example.shopnest` (must match exactly)
   - **App nickname** (optional): `ShopNest`
   - **Debug signing certificate SHA-1** (optional): For Google Sign-In
3. Click **"Register app"**
4. Download the `google-services.json` file
5. Click **"Next"** through remaining steps

#### 3. Add Configuration File
Place the downloaded `google-services.json` in your app module:

```bash
# Move the file to the app directory
cp ~/Downloads/google-services.json /home/technonext/AndroidStudioProjects/ShopNest/app/

# Or manually place it at:
# ShopNest/app/google-services.json
```

**Important**: Ensure the file is placed directly in the `app/` directory, not in subdirectories.

#### 4. Enable Firebase Services

##### Enable Authentication
1. In Firebase Console, go to **Build** → **Authentication**
2. Click **"Get started"**
3. Go to **Sign-in method** tab
4. Enable the following providers:
   - **Email/Password**: Click enable and save
   - **Google** (optional): Enable if you want Google Sign-In
5. Click **"Save"**

##### Enable Cloud Firestore
1. In Firebase Console, go to **Build** → **Firestore Database**
2. Click **"Create database"**
3. Choose **"Start in test mode"** for development (or production mode for live apps)
4. Select your preferred **Cloud Firestore location** (closest to your users)
5. Click **"Enable"**

##### Configure Firestore Security Rules (Recommended)
1. Go to **Firestore Database** → **Rules** tab
2. For development, use these rules:

```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    // Allow authenticated users to read and write their own data
    match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Allow authenticated users to read all products
    match /products/{productId} {
      allow read: if request.auth != null;
      allow write: if false; // Only admins should write products
    }
    
    // Allow authenticated users to manage their own cart
    match /carts/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
  }
}
```

3. Click **"Publish"**

**Note**: Update these rules based on your app's security requirements before production release.

### Local Properties Configuration

Create or verify `local.properties` file in the project root:

```properties
# Location of the Android SDK
sdk.dir=/home/technonext/Android/Sdk

# Or on Windows:
# sdk.dir=C\:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk

# Or on macOS:
# sdk.dir=/Users/YourUsername/Library/Android/sdk
```

**Note**: This file is automatically created by Android Studio and should not be committed to version control.

## 📁 Project Structure

```
ShopNest/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/shopnest/
│   │   │   │   ├── components/       # Reusable UI components
│   │   │   │   ├── model/            # Data models
│   │   │   │   ├── navigation/       # Navigation graph & routes
│   │   │   │   ├── pages/            # Page-level composables
│   │   │   │   ├── screen/           # Screen composables
│   │   │   │   ├── ui/               # UI theme & styling
│   │   │   │   ├── utils/            # Utility functions & helpers
│   │   │   │   ├── viewmodel/        # ViewModels
│   │   │   │   └── MainActivity.kt   # Main entry point
│   │   │   ├── res/                  # Resources (layouts, drawables, etc.)
│   │   │   └── AndroidManifest.xml   # App manifest
│   │   ├── androidTest/              # Instrumented tests
│   │   └── test/                     # Unit tests
│   ├── build.gradle.kts              # App-level build configuration
│   └── google-services.json          # Firebase configuration
├── gradle/
│   ├── libs.versions.toml            # Dependency version catalog
│   └── wrapper/                      # Gradle wrapper
├── build.gradle.kts                  # Project-level build configuration
├── settings.gradle.kts               # Project settings
├── gradlew                           # Gradle wrapper script (Unix)
├── gradlew.bat                       # Gradle wrapper script (Windows)
└── README.md                         # This file
```

## 💻 Development

### Running the App

#### Option 1: From Android Studio (Recommended)
1. **Open the project** in Android Studio
2. **Wait for Gradle sync** to complete (check bottom status bar)
3. **Connect a device** or **start an emulator**:
   - Physical Device: Enable USB debugging in Developer Options
   - Emulator: Tools → Device Manager → Create/Start device
4. **Select target device** from the device dropdown (top toolbar)
5. **Click Run** button (▶️) or press `Shift + F10` (Windows/Linux) / `Control + R` (macOS)
6. Wait for build to complete and app to launch

#### Option 2: From Command Line

**Build and install debug APK:**
```bash
# Navigate to project root
cd /home/technonext/AndroidStudioProjects/ShopNest

# Make gradlew executable (Linux/macOS only, first time)
chmod +x gradlew

# Build debug APK
./gradlew assembleDebug

# Install on connected device/emulator
./gradlew installDebug

# Or combine both steps
./gradlew assembleDebug installDebug

# Run app (if already installed)
adb shell am start -n com.example.shopnest/.MainActivity
```

**On Windows:**
```bash
# Use gradlew.bat instead
gradlew.bat assembleDebug installDebug
```

### Common Development Tasks

#### Clean Build
```bash
# Clean build artifacts
./gradlew clean

# Clean and rebuild
./gradlew clean assembleDebug
```

#### Check Dependencies
```bash
# List all dependencies
./gradlew app:dependencies

# Check for dependency updates
./gradlew dependencyUpdates
```

#### View Build Variants
```bash
# List all build variants
./gradlew tasks
```

### Code Style & Best Practices

This project follows the [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html) and Android best practices.

#### Naming Conventions
- **Composables**: PascalCase (e.g., `ProductCard`, `HomeScreen`)
- **Functions**: camelCase (e.g., `getUserData`, `addToCart`)
- **Variables**: camelCase (e.g., `userName`, `productList`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `MAX_ITEMS`, `API_KEY`)
- **Files**: Match the main class/composable name

#### Code Organization
- Keep functions **small and focused** (single responsibility)
- Use **meaningful names** that describe purpose
- Add **KDoc comments** for public APIs
- Group related code together
- Avoid deep nesting (max 3-4 levels)

#### Jetpack Compose Guidelines
- Prefer **stateless composables** when possible
- Use `remember` for UI state within composables
- Hoist state to appropriate levels
- Use `LaunchedEffect` for side effects
- Leverage `derivedStateOf` for computed state

#### Example Code Style
```kotlin
/**
 * Displays a product card with image, title, and price.
 *
 * @param product The product to display
 * @param onClick Callback when card is clicked
 * @param modifier Optional modifier for customization
 */
@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // Card content
    }
}
```

### Debugging

#### Enable Debug Logging
Add to your `MainActivity` or Application class:
```kotlin
if (BuildConfig.DEBUG) {
    // Enable debug logging
    FirebaseFirestore.setLoggingEnabled(true)
}
```

#### Using Android Studio Debugger
1. Set breakpoints by clicking line numbers
2. Run app in **Debug mode** (Shift + F9)
3. Use **Logcat** to view logs (View → Tool Windows → Logcat)

#### Common Issues

**Issue**: Gradle sync failed
```bash
# Solution: Clear Gradle cache
./gradlew clean
rm -rf ~/.gradle/caches/
# Then sync again in Android Studio
```

**Issue**: App crashes on startup
```bash
# Check Logcat for stack traces
# Verify google-services.json is in app/ directory
# Ensure Firebase services are enabled
```

### Testing

#### Run Unit Tests
```bash
# Run all unit tests
./gradlew test

# Run tests for debug variant
./gradlew testDebugUnitTest

# Generate test report
./gradlew test --tests "*" --info
# Report location: app/build/reports/tests/testDebugUnitTest/index.html
```

#### Run Instrumented Tests (UI/Integration)
```bash
# Ensure device/emulator is connected
adb devices

# Run all instrumented tests
./gradlew connectedAndroidTest

# Run specific test class
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.example.shopnest.ExampleInstrumentedTest
```

#### Run All Tests
```bash
# Run both unit and instrumented tests
./gradlew test connectedAndroidTest
```

#### Code Coverage (Optional)
```bash
# Generate code coverage report
./gradlew testDebugUnitTest jacocoTestReport
# Report: app/build/reports/jacoco/jacocoTestReport/html/index.html
```

## 🔨 Building

### Debug Build

Build a debug APK for testing and development:

```bash
# Build debug APK
./gradlew assembleDebug

# Output location
# app/build/outputs/apk/debug/app-debug.apk
```

**Debug APK Features:**
- Includes debug symbols
- Debuggable via Android Studio
- Not optimized (larger file size)
- Signed with debug keystore

### Release Build

Build an optimized release APK:

```bash
# Build unsigned release APK
./gradlew assembleRelease

# Output location
# app/build/outputs/apk/release/app-release-unsigned.apk
```

### Generate Signed APK/AAB for Production

#### Step 1: Create a Keystore (First Time Only)

```bash
# Generate a new keystore
keytool -genkey -v -keystore shopnest-release.keystore \
  -alias shopnest \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000

# You'll be prompted for:
# - Keystore password (remember this!)
# - Key password
# - Your name and organization details
```

**Important**: 
- Store your keystore file safely (backup to secure location)
- Never commit keystore to version control
- Keep passwords secure (use password manager)

#### Step 2: Configure Signing in Gradle

Create `keystore.properties` in project root:

```properties
storePassword=YourKeystorePassword
keyPassword=YourKeyPassword
keyAlias=shopnest
storeFile=../shopnest-release.keystore
```

Add to `.gitignore`:
```
keystore.properties
*.keystore
*.jks
```

Update `app/build.gradle.kts`:

```kotlin
// Load keystore properties
val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

android {
    // ... existing config ...
    
    signingConfigs {
        create("release") {
            if (keystorePropertiesFile.exists()) {
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
                storeFile = file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
            }
        }
    }
    
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
}
```

#### Step 3: Build Signed APK

```bash
# Build signed release APK
./gradlew assembleRelease

# Output location
# app/build/outputs/apk/release/app-release.apk
```

#### Step 4: Build Android App Bundle (AAB) for Play Store

```bash
# Build release AAB (recommended for Play Store)
./gradlew bundleRelease

# Output location
# app/build/outputs/bundle/release/app-release.aab
```

**Why AAB?**
- Smaller download size for users
- Google Play dynamic delivery
- Required for new apps on Play Store
- Automatic APK generation per device

### Build Variants

View available build variants:
```bash
./gradlew tasks --all | grep assemble
```

### Verify APK

After building, verify the APK:

```bash
# Get APK information
aapt dump badging app/build/outputs/apk/release/app-release.apk

# Check if APK is signed
jarsigner -verify -verbose -certs app/build/outputs/apk/release/app-release.apk

# Install and test
adb install app/build/outputs/apk/release/app-release.apk
```

### Build Optimization Tips

1. **Enable R8 Optimization** (already configured):
   - Shrinks code
   - Obfuscates code
   - Optimizes bytecode

2. **Reduce APK Size**:
   - Use WebP images instead of PNG/JPG
   - Enable resource shrinking
   - Use Android App Bundles

3. **ProGuard Rules**:
   - Keep necessary classes
   - Add rules for third-party libraries
   - Test thoroughly after obfuscation

### Build Performance

Speed up builds:

```bash
# Enable parallel execution (gradle.properties)
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.configureondemand=true

# Increase heap size
org.gradle.jvmargs=-Xmx4096m -XX:MaxMetaspaceSize=512m
```

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork the Repository**
   ```bash
   git clone https://github.com/yourusername/ShopNest.git
   ```

2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/AmazingFeature
   ```

3. **Commit Your Changes**
   ```bash
   git commit -m 'Add some AmazingFeature'
   ```

4. **Push to the Branch**
   ```bash
   git push origin feature/AmazingFeature
   ```

5. **Open a Pull Request**

### Contribution Guidelines

- Write clean, maintainable code
- Follow the existing code style and Kotlin conventions
- Add tests for new features
- Update documentation as needed
- Ensure all tests pass before submitting PR
- Provide detailed PR descriptions with screenshots if applicable
- Reference any related issues in your PR

### Code Review Process

1. All submissions require review before merging
2. Maintainers will review your PR and may request changes
3. Once approved, your PR will be merged
4. Your contribution will be credited in the release notes

### Areas for Contribution

- 🐛 Bug fixes
- ✨ New features
- 📝 Documentation improvements
- 🎨 UI/UX enhancements
- ✅ Test coverage
- 🌐 Translations
- ⚡ Performance improvements

## 🗓️ Roadmap

### Phase 1: Core Features (Current)
- [x] Project setup and architecture
- [x] Firebase integration
- [x] User authentication
- [x] Basic UI/UX with Compose
- [ ] Product catalog implementation
- [ ] Shopping cart functionality
- [ ] Category-based filtering
- [ ] Product detail pages

### Phase 2: Enhanced Features (Q2 2026)
- [ ] Payment gateway integration
- [ ] Order management system
- [ ] User profiles and preferences
- [ ] Product search and filtering
- [ ] Wishlist functionality
- [ ] Order history
- [ ] Address management
- [ ] Email notifications

### Phase 3: Advanced Features (Q3 2026)
- [ ] Push notifications
- [ ] Product recommendations
- [ ] Reviews and ratings
- [ ] Social sharing
- [ ] Multi-language support
- [ ] Dark mode theme
- [ ] In-app chat support
- [ ] Promotional codes/coupons

### Phase 4: Optimization & Polish (Q4 2026)
- [ ] Performance optimization
- [ ] Offline mode support
- [ ] Analytics integration
- [ ] Comprehensive testing
- [ ] Security audit
- [ ] Accessibility improvements
- [ ] Production release
- [ ] Play Store deployment

### Future Enhancements
- [ ] AR product preview
- [ ] Voice search
- [ ] Biometric authentication
- [ ] Multi-currency support
- [ ] Seller dashboard
- [ ] Admin panel
- [ ] Real-time inventory tracking
- [ ] Live chat with sellers

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2026 ShopNest

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 📞 Contact

**Project Maintainer**: Your Name

- 📧 Email: ahmed777emran@gmail.com
- 💼 GitHub: [@EmranZZ](https://github.com/EmranZZ)

**Project Link**: [https://github.com/EmranZZ/ShopNest](https://github.com/EmranZZ/ShopNest)

---

## 🙏 Acknowledgments

- [Android Developers](https://developer.android.com/) - Official Android documentation
- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern UI toolkit
- [Firebase](https://firebase.google.com/) - Backend services
- [Material Design 3](https://m3.material.io/) - Design guidelines
- [Kotlin](https://kotlinlang.org/) - Programming language
- [Coil](https://coil-kt.github.io/coil/) - Image loading library
- Open source community for continuous inspiration and support

---

<div align="center">

**Made with ❤️ using Jetpack Compose**

⭐ **Star this repository if you find it helpful!** ⭐

[Report Bug](https://github.com/EmranZZ/ShopNest/issues) · [Request Feature](https://github.com/EmranZZ/ShopNest/issues) · [Contribute](https://github.com/EmranZZ/ShopNest/pulls)

</div>

