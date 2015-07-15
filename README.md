# GingerMaterialRepo
An Eclipse IDE supported library to bring fully animated Material Design components to pre-Lolipop Android. Ref.:http://rey5137.com/material/

GingerMaterialRepo
=====================
MaterialLibrary is an Open Source Android library that back-port Material Design components to pre-Lolipop Android. MaterialLibrary's original author is [Rey Pham](https://github.com/rey5137).

* [Features](#features)
* [Getting Started](#getting-started)
* [Contributing](#contributing)

##Features
- [Progress](https://github.com/rey5137/Material/wiki/Progress)
    - Circular
    
        ![](https://github.com/rey5137/Material/raw/master/image/progress_circular_indeterminate.gif) ![](https://github.com/rey5137/Material/raw/master/image/progress_circular_determinate.gif)
    - Linear

        ![](https://github.com/rey5137/Material/raw/master/image/progress_linear_indeterminate.gif) 
        ![](https://github.com/rey5137/Material/raw/master/image/progress_linear_determinate.gif)
        ![](https://github.com/rey5137/Material/raw/master/image/progress_linear_query.gif)
        ![](https://github.com/rey5137/Material/raw/master/image/progress_linear_buffer.gif)

- [Button](https://github.com/rey5137/Material/wiki/Button)
    
    ![](https://github.com/rey5137/Material/raw/master/image/button_raise_touch.gif) ![](https://github.com/rey5137/Material/raw/master/image/button_raise_wave.gif)

    ![](https://github.com/rey5137/Material/raw/master/image/fab_image.gif) ![](https://github.com/rey5137/Material/raw/master/image/fab_line.gif)   
     
- [Switch](https://github.com/rey5137/Material/wiki/Switch)

    ![](https://github.com/rey5137/Material/raw/master/image/cb.gif)

    ![](https://github.com/rey5137/Material/raw/master/image/rb.gif)

    ![](https://github.com/rey5137/Material/raw/master/image/switch.gif)

- [Slider](https://github.com/rey5137/Material/wiki/Slider)

    ![](https://github.com/rey5137/Material/raw/master/image/slider_continuous.gif)

    ![](https://github.com/rey5137/Material/raw/master/image/slider_discrete.gif)

- [Spinner](https://github.com/rey5137/Material/wiki/Spinner)
     
    ![](https://github.com/rey5137/Material/raw/master/image/spn.gif)

- [Text Field](https://github.com/rey5137/Material/wiki/Text-Field)

    ![](https://github.com/rey5137/Material/raw/master/image/textfield.gif)

- [TabPageIndicator](https://github.com/rey5137/Material/wiki/TabPageIndicator)
     
    ![](https://github.com/rey5137/Material/raw/master/image/tpi.gif)

- [SnackBar](https://github.com/rey5137/Material/wiki/SnackBar)
     
    ![](https://github.com/rey5137/Material/raw/master/image/snackbar.png)

- [Dialog](https://github.com/rey5137/Material/wiki/Dialog)

    ![](https://github.com/rey5137/Material/raw/master/image/dialog_3.png) ![](https://github.com/rey5137/Material/raw/master/image/dialog_4.png)


## Getting Started

AppCompat and CardView library is required by Material library.

```
   com.android.support:appcompat-v7:21.0.2
   com.android.support:cardview-v7:21.0.3
```
Now you can use any widget in **com.hanry.material.widget** package as you wish. For styling, please view [Wiki](https://github.com/rey5137/Material/wiki). Note that default style of widgets depend on theme of AppCompat. Here is an example:

```xml
     <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->

        <!-- colorPrimary is used for the default action bar background -->
        <item name="colorPrimary">@color/colorPrimary</item>

        <!-- colorPrimaryDark is used for the status bar -->
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>

        <!-- colorAccent is used as the default value for colorControlActivated
             which is used to tint widgets -->
        <item name="colorAccent">@color/colorAccent</item>

        <!-- You can also set colorControlNormal, colorControlActivated
             colorControlHighlight & colorSwitchThumbNormal. -->
    </style>
```

## Contributing
Want to contribute? You are welcome! 
Note that all pull request should go to `dev` branch.

Developed By
------------

* Rey Pham - <pea5137@gmail.com>

Changed for Eclipse IDE By
------------

* Hanif Abuvani - <hanif.abuvani@gmail.com>

License
--------

    Copyright 2015 Rey Pham.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
