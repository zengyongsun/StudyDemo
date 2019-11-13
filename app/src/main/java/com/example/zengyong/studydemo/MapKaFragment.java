package com.example.zengyong.studydemo;
//
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.Bundle;
//import android.os.Environment;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.RotateAnimation;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
//import com.esri.arcgisruntime.data.Feature;
//import com.esri.arcgisruntime.data.FeatureCollection;
//import com.esri.arcgisruntime.data.FeatureCollectionTable;
//import com.esri.arcgisruntime.data.Field;
//import com.esri.arcgisruntime.geometry.GeometryType;
//import com.esri.arcgisruntime.geometry.Point;
//import com.esri.arcgisruntime.geometry.PointCollection;
//import com.esri.arcgisruntime.geometry.Polyline;
//import com.esri.arcgisruntime.geometry.SpatialReferences;
//import com.esri.arcgisruntime.layers.FeatureCollectionLayer;
//import com.esri.arcgisruntime.layers.RasterLayer;
//import com.esri.arcgisruntime.loadable.LoadStatus;
//import com.esri.arcgisruntime.mapping.ArcGISMap;
//import com.esri.arcgisruntime.mapping.MobileMapPackage;
//import com.esri.arcgisruntime.mapping.view.BackgroundGrid;
//import com.esri.arcgisruntime.mapping.view.Graphic;
//import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
//import com.esri.arcgisruntime.mapping.view.MapRotationChangedEvent;
//import com.esri.arcgisruntime.mapping.view.MapRotationChangedListener;
//import com.esri.arcgisruntime.mapping.view.MapScaleChangedEvent;
//import com.esri.arcgisruntime.mapping.view.MapScaleChangedListener;
//import com.esri.arcgisruntime.mapping.view.MapView;
//import com.esri.arcgisruntime.raster.Raster;
//import com.esri.arcgisruntime.symbology.PictureMarkerSymbol;
//import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
//import com.esri.arcgisruntime.symbology.SimpleRenderer;
//import com.esri.arcgisruntime.symbology.TextSymbol;
//import com.zy.generallib.MyLog;
//import com.zy.generallib.MyToast;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//
//
///**
// * @author : Zeyo
// * e-mail : zengyongsun@163.com
// * date   : 2019/5/31 11:28
// * desc   :
// * version: 1.0
// */
public class MapKaFragment {
//    extends
//} Fragment implements MapKaContract.View {
//
//    private static final String TAG = "MapKaFragment";
//    @BindView(R.id.mapView)
//    MapView mMapView;
//
//    @BindView(R.id.tvSpeed)
//    TextView tvSpeed;
//
//    @BindView(R.id.ivZl)
//    ImageView ivZl;
//    @BindView(R.id.tvScale)
//    TextView tvScale;
//
//    @BindView(R.id.testLayout)
//    LinearLayout testLayout;
//    @BindView(R.id.tvNoSpeed)
//    TextView tvNoSpeed;
//
//    private Unbinder unbinder;
//
//    private GraphicsOverlay mLocationOverlay;
//    private GraphicsOverlay mGraphicsOverlay;
//    private MapKaContract.Presenter mPresenter;
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud7176629939,none,YYPJD4SZ8P0LA1GJH245");
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_map_layout, container, false);
//        unbinder = ButterKnife.bind(this, root);
//        mapInit();
//        new MapKaPresenter(new MapKaModel(), this);
//        return root;
//    }
//
//    float lastBearing = 0;
//
//    /***
//     *设置ImageView旋转动画
//     * @param bearing
//     * lastBearing为上次icon旋转之后所处的角度,bearing为要旋转到的角度位置
//     */
//    private void startIvCompass(float bearing) {
//        bearing = 360 - bearing;
//        RotateAnimation rotateAnimation = new RotateAnimation(lastBearing, bearing, Animation.RELATIVE_TO_SELF,
//                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//        rotateAnimation.setFillAfter(true);
//        ivZl.startAnimation(rotateAnimation);
//        lastBearing = bearing;
//    }
//
//    public String getScaleText(double scale) {
//        //转成厘米：米
//        scale /= 100;
//        String formatStr = null;
//        if (scale >= 1000) {
//            scale /= 1000;
//            formatStr = "%1$.1f公里";
//        } else if (scale >= 1) {
//            formatStr = "%1$.1f米";
//        } else if (scale >= 0.1) {
//            scale *= 10;
//            formatStr = "%1$.1f分米";
//        } else {
//            scale *= 100;
//            formatStr = "%1$.1f厘米";
//        }
//        return String.format(formatStr, (float) scale);
//    }
//
//    @Override
//    public void showToastMessage(String message) {
//        MyToast.showShort(viewContext(), message);
//    }
//
//
//    @Override
//    public void onPause() {
//        if (mMapView != null) {
//            mMapView.pause();
//        }
//        super.onPause();
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        mPresenter.start();
//        if (mMapView != null) {
//            mMapView.resume();
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        if (mMapView != null) {
//            mMapView.dispose();
//        }
//        super.onDestroy();
//    }
//
//    @Override
//    public void showLocation(double lat, double lont) {
//        if (lat != 0 && lont != 0) {
//            positioning(lont, lat);
//        } else {
//            clearLocationPoint();
//        }
//    }
//
//    @Override
//    public void showSpeed(String speed) {
//        if (tvSpeed != null) {
//            tvSpeed.setText(speed);
//        }
//    }
//
//    @Override
//    public void showPoint() {
//
//    }
//
//    @Override
//    public void showLine() {
//        createPolylineGraphics();
//        createPolylineGraphicsUp();
//    }
//
//    @Override
//    public void showImageInMap() {
////        createTextGraphics();
////        createImgGraphics();
////        createFeatureCollection();
//    }
//
//    @Override
//    public void showBaseMap() {
//        loadLocalFile();
////        setupOfflineMap();
//    }
//
//    @Override
//    public void showMaxSpeed(String max) {
//        tvNoSpeed.setText(max);
//    }
//
//    @Override
//    public void mapInit() {
//        setBackgroundGrid();
////        ArcGISRuntimeEnvironment.setLicense("runtimelite,1000,rud7176629939,none,YYPJD4SZ8P0LA1GJH245");
//        mMapView.setAttributionTextVisible(false);
//        mMapView.setViewpointRotationAsync(0);
//        showBaseMap();
//        mMapView.addMapRotationChangedListener(new MapRotationChangedListener() {
//            @Override
//            public void mapRotationChanged(MapRotationChangedEvent mapRotationChangedEvent) {
//                //map.getMapRotation()为地图旋转的角度
//                startIvCompass((float) mMapView.getMapRotation());
//            }
//        });
//        //比例尺改变的监听
//        mMapView.addMapScaleChangedListener(new MapScaleChangedListener() {
//            @Override
//            public void mapScaleChanged(MapScaleChangedEvent mapScaleChangedEvent) {
//                if (mMapView != null) {
//                    MyLog.d(TAG, mMapView.getMapScale() + "");
//                    tvScale.setText(getScaleText(mMapView.getMapScale()));
//                }
//            }
//        });
//        createGraphicsOverlay();
//        createLocationOverlay();
//    }
//
//    @Override
//    public Context viewContext() {
//        return getContext();
//    }
//
//    @Override
//    public void setPresenter(MapKaContract.Presenter presenter) {
//        mPresenter = presenter;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//    private void createGraphicsOverlay() {
//        mGraphicsOverlay = new GraphicsOverlay();
//        mMapView.getGraphicsOverlays().add(mGraphicsOverlay);
//    }
//
//    private void createLocationOverlay() {
//        mLocationOverlay = new GraphicsOverlay();
//        mMapView.getGraphicsOverlays().add(mLocationOverlay);
//    }
//
//    private void positioning(double longtX, double latY) {
//        if (isAdded()) {
//            clearLocationPoint();
//            Point point = new Point(longtX, latY, SpatialReferences.getWgs84());
//            PictureMarkerSymbol textSymbol = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.mipmap.icon_map_location));
//            Graphic textGraphic = new Graphic(point, textSymbol);
//            mLocationOverlay.getGraphics().add(textGraphic);
//        }
//    }
//
//    private void clearLocationPoint() {
//        if (mLocationOverlay != null && mLocationOverlay.getGraphics() != null) {
//            mLocationOverlay.getGraphics().clear();
//        }
//    }
//
//    private void createTextGraphics() {
//        // 一号铲车
//        Point point1 = new Point(111.9556776248, 27.4108931722, SpatialReferences.getWgs84());
//        TextSymbol textSymbol1 = new TextSymbol();
//        textSymbol1.setColor(Color.RED);
//        textSymbol1.setText("2#/3#PC400");
//        Graphic textGraphic1 = new Graphic(point1, textSymbol1);
//        mGraphicsOverlay.getGraphics().add(textGraphic1);
//
//        //二号铲车
//        Point point2 = new Point(111.9615374261, 27.4115543287, SpatialReferences.getWgs84());
//        TextSymbol textSymbol2 = new TextSymbol();
//        textSymbol2.setColor(Color.RED);
//        textSymbol2.setText("1#/2#CAT988G");
//        Graphic textGraphic2 = new Graphic(point2, textSymbol2);
//        mGraphicsOverlay.getGraphics().add(textGraphic2);
//
//        // 三号铲车
//        Point point3 = new Point(111.9575343387, 27.4118123176, SpatialReferences.getWgs84());
//        TextSymbol textSymbol3 = new TextSymbol();
//        textSymbol3.setColor(Color.RED);
//        textSymbol3.setText("R984C");
//        Graphic textGraphic3 = new Graphic(point3, textSymbol3);
//        mGraphicsOverlay.getGraphics().add(textGraphic3);
//
//    }
//
//    private void createImgGraphics() {
//        positioning(111.9589128657, 27.4091667796);
//        Point point = new Point(111.9583085587, 27.4094428381, SpatialReferences.getWgs84());
//        PictureMarkerSymbol textSymbol = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.mipmap.icon_up));
//        Graphic textGraphic = new Graphic(point, textSymbol);
//        mGraphicsOverlay.getGraphics().add(textGraphic);
//    }
//
//    private void createPolylineGraphics() {
//        PointCollection polylinePoints = new PointCollection(SpatialReferences.getWgs84());
//        polylinePoints.add(new Point(111.9589011322, 27.4091928224));
//        polylinePoints.add(new Point(111.9586840487, 27.4092032421));
//        polylinePoints.add(new Point(111.9583085587, 27.4094428381));
//        polylinePoints.add(new Point(111.9579565411, 27.4098959805));
//        polylinePoints.add(new Point(111.9577042665, 27.4104949559));
//        polylinePoints.add(new Point(111.9575400076, 27.4117241452));
//        polylinePoints.add(new Point(111.9575396886, 27.4118016340));
//        Polyline polyline = new Polyline(polylinePoints);
//        SimpleLineSymbol polylineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.parseColor("#5E92F8"), 3.0f);
//        Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);
//        mGraphicsOverlay.getGraphics().add(polylineGraphic);
//    }
//
//    private void createPolylineGraphicsUp() {
//        PointCollection polylinePoints = new PointCollection(SpatialReferences.getWgs84());
//        polylinePoints.add(new Point(111.9577795072, 27.4103278183));
//        polylinePoints.add(new Point(111.9580385224, 27.4101155694));
//        polylinePoints.add(new Point(111.9578492421, 27.4101686317));
//        polylinePoints.add(new Point(111.9577844881, 27.4100403978));
//        polylinePoints.add(new Point(111.9577795072, 27.4103278183));
//        Polyline polyline = new Polyline(polylinePoints);
//        SimpleLineSymbol polylineSymbol = new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, Color.parseColor("#009999"), 3.0f);
//        Graphic polylineGraphic = new Graphic(polyline, polylineSymbol);
//        mGraphicsOverlay.getGraphics().add(polylineGraphic);
//    }
//
//    private void createFeatureCollection() {
//        if (mMapView != null) {
//            FeatureCollection featureCollection = new FeatureCollection();
//            FeatureCollectionLayer featureCollectionLayer = new FeatureCollectionLayer(featureCollection);
//            mMapView.getMap().getOperationalLayers().add(featureCollectionLayer);
//            createPointTable(featureCollection);
//        }
//    }
//
//    private void createPointTable(FeatureCollection featureCollection) {
//        List<Feature> features = new ArrayList<>();
//        List<Field> pointFields = new ArrayList<>();
//        pointFields.add(Field.createString("Place", "Place Name", 50));
//        FeatureCollectionTable pointsTable = new FeatureCollectionTable(pointFields, GeometryType.POINT, SpatialReferences.getWgs84());
//        PictureMarkerSymbol textSymbol = new PictureMarkerSymbol((BitmapDrawable) getResources().getDrawable(R.mipmap.chanche));
//        SimpleRenderer renderer = new SimpleRenderer(textSymbol);
//        pointsTable.setRenderer(renderer);
//        featureCollection.getTables().add(pointsTable);
//
//        // 一号铲车
//        Map<String, Object> attributes1 = new HashMap<>();
//        attributes1.put(pointFields.get(0).getName(), "Malibu Pier");
//        Point point1 = new Point(111.9556786259, 27.4110470204, SpatialReferences.getWgs84());
//        features.add(pointsTable.createFeature(attributes1, point1));
//
//        //二号铲车
//        Map<String, Object> attributes4 = new HashMap<>();
//        attributes1.put(pointFields.get(0).getName(), "Malibu Pier");
//        Point point4 = new Point(111.9615315317, 27.4117138977, SpatialReferences.getWgs84());
//        features.add(pointsTable.createFeature(attributes4, point4));
//
//        // 三号铲车
//        Map<String, Object> attributes2 = new HashMap<>();
//        attributes2.put(pointFields.get(0).getName(), "Malibu Hindi Temple");
//        Point point2 = new Point(111.9575250482, 27.4119448744, SpatialReferences.getWgs84());
//        features.add(pointsTable.createFeature(attributes2, point2));
//
//        pointsTable.addFeaturesAsync(features);
//    }
//
//    /**
//     * 去掉背景的格子
//     */
//    private void setBackgroundGrid() {
//        BackgroundGrid backgroundGrid = new BackgroundGrid();
//        backgroundGrid.setColor(R.color.colorPrimary);
//        backgroundGrid.setGridLineWidth(0);
//        mMapView.setBackgroundGrid(backgroundGrid);
//    }
//
//    private void loadLocalFile() {
//        //加载影像  Export19-50-30.tif
//        String imgPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + "sf_map.tif";
//        Log.d(TAG, "loadFile: " + imgPath);
//        Raster raster = new Raster(imgPath);
//        RasterLayer rasterLayer = new RasterLayer(raster);
//        ArcGISMap map = new ArcGISMap();
//        map.getOperationalLayers().add(rasterLayer);
//        mMapView.setMap(map);
//    }
//
//    /**
//     * 设置离线的地图，mmpk格式
//     */
//    private void setupOfflineMap() {
//        if (mMapView != null) {
//            String imgPath = Environment.getExternalStorageDirectory()
//                    .getAbsolutePath();
//            File mmpkFile = new File(imgPath, "offline-maps-package.mmpk");
//
//            final MobileMapPackage mapPackage = new MobileMapPackage(mmpkFile.getAbsolutePath());
//            mapPackage.addDoneLoadingListener(() -> {
//                if (mapPackage.getLoadStatus() == LoadStatus.LOADED && !mapPackage.getMaps().isEmpty()) {
//                    mMapView.setMap(mapPackage.getMaps().get(0));
//                } else {
//                    Log.e("setupOfflineMap", "Cannot load " + imgPath + "/offline-maps-package.mmpk");
//                }
//            });
//            mapPackage.loadAsync();
//        }
//    }
//
}
