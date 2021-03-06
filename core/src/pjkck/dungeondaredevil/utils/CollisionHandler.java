package pjkck.dungeondaredevil.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class CollisionHandler {

    private TiledMap map;

    public CollisionHandler(TiledMap map) {
        this.map = map;
    }

    public boolean isCollidingWithMap(Rectangle rectHitbox, int nLayer) {
        // Check if hitbox overlaps TiledMap Rectangle object
        for (MapObject object : map.getLayers().get(nLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            if (rectHitbox.overlaps(rect)) {
                return true;
            }
        }

        return false;
    }

    public boolean isSpriteColliding(Rectangle rectHitbox1, Rectangle rectHitbox2) {
        // Check if two rectangles are overlapping
        return rectHitbox1.overlaps(rectHitbox2);
    }

    public float findDistance(Vector2 vPos, Vector2 vPos2) {
        // Calculate distance between bullet and a point
        return (float) Math.sqrt(Math.pow((vPos.x - vPos2.x), 2) + Math.pow((vPos.y - vPos2.y), 2));
    }
}
