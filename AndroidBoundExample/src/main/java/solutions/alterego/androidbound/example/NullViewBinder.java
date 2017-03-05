package solutions.alterego.androidbound.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import solutions.alterego.androidbound.android.FontManager;
import solutions.alterego.androidbound.android.interfaces.IFontManager;
import solutions.alterego.androidbound.android.interfaces.IImageLoader;
import solutions.alterego.androidbound.binding.interfaces.IBindingAssociationEngine;
import solutions.alterego.androidbound.converters.interfaces.IValueConverter;
import solutions.alterego.androidbound.interfaces.ILogger;
import solutions.alterego.androidbound.interfaces.IViewBinder;
import solutions.alterego.androidbound.viewresolvers.interfaces.IViewResolver;

public class NullViewBinder implements IViewBinder {

    private Context mContext;

    private IFontManager mFontManager;

    public NullViewBinder() {
        setFontManager(new FontManager(getLogger()));
    }

    @Override
    public void clearBindingForViewAndChildren(View rootView) {

    }

    @Override
    public void clearBindingsFor(View view) {

    }

    @Override
    public void clearAllBindings() {

    }

    @Override
    public View inflate(Context context, Object source, int layoutResID, ViewGroup viewGroup) {
        return ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutResID, viewGroup);
    }

    @Override
    public View inflate(Context context, Object source, int layoutResID, ViewGroup viewGroup, IViewResolver additionalResolver) {
        return ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(layoutResID, viewGroup);
    }

    @Override
    public void registerBindingsFor(View view, List<IBindingAssociationEngine> bindings) {

    }

    @Override
    public List<IBindingAssociationEngine> getBindingsForViewAndChildren(View rootView) {
        return null;
    }

    @Override
    public List<IBindingAssociationEngine> getBindingsFor(View view) {
        return null;
    }

    @Override
    public void registerViewResolver(IViewResolver resolver) {

    }

    @Override
    public void unregisterViewResolver(IViewResolver resolver) {

    }

    @Override
    public void registerLazyBindingsFor(View view, String bindingString) {

    }

    @Override
    public void lazyBindView(View view, Object source) {

    }

    @Override
    public void bindViewToSource(Object source, View view, String bindingString) {

    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void setContext(Context context) {
        mContext = context;
    }

    @Override
    public IFontManager getFontManager() {
        return mFontManager;
    }

    @Override
    public void setFontManager(IFontManager fontManager) {
        mFontManager = fontManager;
    }

    @Override
    public void setImageLoader(IImageLoader imageLoader) {

    }

    @Override
    public void registerConverter(String name, IValueConverter converter) {

    }

    @Override
    public void dispose() {

    }

    @Override
    public ILogger getLogger() {
        return null;
    }

    @Override
    public void registerResource(String name, Object resource) {

    }
}
