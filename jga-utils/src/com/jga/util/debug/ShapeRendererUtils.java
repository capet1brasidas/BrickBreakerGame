package com.jga.util.debug;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.jga.util.Validate;
import com.badlogic.gdx.utils.Logger;

public class ShapeRendererUtils {
    private static final Logger log=new Logger(ShapeRendererUtils.class.getName(),Logger.DEBUG);
    //constructors
    private ShapeRendererUtils(){
    }

    //public methods
    public static void rect(ShapeRenderer renderer, Rectangle rectangle){

            Validate.notNull(renderer);
            Validate.notNull(rectangle);




        renderer.rect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
    }

    public static void circle(ShapeRenderer renderer, Circle circle){
        Validate.notNull(renderer);
        Validate.notNull(circle);

        renderer.circle(circle.x,circle.y,circle.radius,15);


    }

    public static void polygon(ShapeRenderer renderer, Polygon polygon){
        //validate params
        Validate.notNull(renderer);
        Validate.notNull(polygon);

         renderer.polyline(polygon.getVertices());

//         float[] polygonSuspect=polygon.getTransformedVertices();
//        log.debug("polygon size is "+polygonSuspect.length);
//        for (int i = 0; i < polygonSuspect.length; i++) {
//            log.debug("polygon "+i+" is"+polygonSuspect[i]);
//
//        }

        renderer.polygon(polygon.getTransformedVertices());
        renderer.polygon(polygon.getVertices());


    }


}
