# HollyViewPager

[![screen](http://j.gifs.com/vZ4D9G.gif)](https://www.youtube.com/watch?v=4ZmjEde-Xho)

#Usage

Add a HollyViewPager in your layout

```xml
<com.github.florent37.hollyviewpager.HollyViewPager
        android:id="@+id/hollyViewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:hvp_headerHeight="150dp" />
```
        
```java
HollyViewPager hollyViewPager = findViewById(R.id.hollyViewPager);
hollyViewPager.setAdapter(...);
```

#RecyclerView
```java	
HollyViewPagerBus.registerRecyclerView(getActivity(), recyclerView);
```

Adapter
```java
	protected static final int TYPE_HEADER = 0;
    protected static final int TYPE_CELL = 1;

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0: return TYPE_HEADER;
            default: return TYPE_CELL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        View view;
        switch (type){
            case TYPE_HEADER:
                new RecyclerView.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hvp_header_placeholder,viewGroup,false)) {};
                break;
            default:
                ...YOUR_VIEW...
                break;
        }
    }
```

#ScrollView

```java	
HollyViewPagerBus.registerScrollView(getActivity(), scrollView);
```

```xml	
<com.github.ksoichiro.android.observablescrollview.ObservableScrollView 
	xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/scrollView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scrollbars="none">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <include layout="@layout/hvp_header_placeholder"/>

        ... YOUR CONTENT ...
```

#Download

In your module [![Download](https://api.bintray.com/packages/florent37/maven/HollyViewPager/images/download.svg)](https://bintray.com/florent37/maven/HollyViewPager/_latestVersion)
```groovy
compile 'com.github.florent37:hollyviewpager:1.0.0'
```

#Credits

Author: Florent Champigny

<a href="https://plus.google.com/+florentchampigny">
  <img alt="Follow me on Google+"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/gplus.png" />
</a>
<a href="https://twitter.com/florent_champ">
  <img alt="Follow me on Twitter"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/twitter.png" />
</a>
<a href="https://www.linkedin.com/profile/view?id=297860624">
  <img alt="Follow me on LinkedIn"
       src="https://raw.githubusercontent.com/florent37/DaVinci/master/mobile/src/main/res/drawable-hdpi/linkedin.png" />
</a>


License
--------

    Copyright 2015 florent37, Inc.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
