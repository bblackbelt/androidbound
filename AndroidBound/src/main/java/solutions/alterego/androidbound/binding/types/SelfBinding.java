package solutions.alterego.androidbound.binding.types;

import java.security.InvalidParameterException;

import io.reactivex.disposables.Disposable;
import solutions.alterego.androidbound.binding.interfaces.INotifyPropertyChanged;
import solutions.alterego.androidbound.interfaces.ILogger;

public class SelfBinding extends BindingBase {

    private Disposable mDisposable;

    public SelfBinding(Object subject, ILogger logger) {
        super(subject, logger);
        setupBinding(subject);
    }

    private void setupBinding(Object subject) {
        if (subject == null) {
            return;
        }

        if (getSubject() instanceof INotifyPropertyChanged) {
            setupChanges(true);
            getLogger().debug("Subject implements INotifyPropertyChanged. Subscribing...");

            mDisposable = ((INotifyPropertyChanged) subject)
                    .onPropertyChanged()
                    .filter(member -> member.equals("this"))
                    .subscribe(s -> {
                        onBoundPropertyChanged();
                    });
        } else {
            setupChanges(false);
        }
    }

    protected void onBoundPropertyChanged() {
        notifyChange(getValue());
    }

    @Override
    public Class<?> getType() {
        return getSubject().getClass();
    }

    @Override
    public Object getValue() {
        return getSubject();
    }

    @Override
    public void setValue(Object value) {
        throw new InvalidParameterException("Cannot set the value of a SelfBinding");
    }

    @Override
    public void addValue(Object object) {
        throw new InvalidParameterException("Cannot add the value of a SelfBinding");
    }

    @Override
    public void removeValue(Object result) {
        throw new InvalidParameterException("Cannot add the value of a SelfBinding");
    }

    @Override
    public void dispose() {
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }
        super.dispose();
    }
}
