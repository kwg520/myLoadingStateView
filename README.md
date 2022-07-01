```
# LoadingStateView
```

To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file

```css
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

**Step 2.** Add the dependency

```css
	dependencies {
	        implementation 'com.github.kwg520:myLoadingStateView:Tag'
	}
```