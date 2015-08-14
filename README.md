# HollyViewPager

[![http://j.gifs.com/vZ4D9G.gif](https://www.youtube.com/watch?v=T51vi8ltZ5k)]

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