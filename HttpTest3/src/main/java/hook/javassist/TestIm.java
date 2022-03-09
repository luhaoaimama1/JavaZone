package hook.javassist;

import java.lang.ref.SoftReference;

public class TestIm {
    private SoftReference<Entity> ab;

    public final Entity getAb() {
        if (ab != null && ab.get() != null) {
            return ab.get();
        } else {
            Entity entity = new Entity();
            entity.setName("name?");
            ab = new SoftReference<Entity>(entity);
        }
        return ab.get();
    }
}
