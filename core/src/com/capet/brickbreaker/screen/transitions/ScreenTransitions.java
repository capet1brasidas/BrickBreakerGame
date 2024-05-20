package com.capet.brickbreaker.screen.transitions;

import com.jga.util.screen.transition.ScreenTransition;
import com.jga.util.screen.transition.impl.FadeScreenTransition;
import com.jga.util.screen.transition.impl.SlideScreenTransition;

public final class ScreenTransitions {
    public static final ScreenTransition FADE=new FadeScreenTransition(1f);

    public static final ScreenTransition SCALE=new FadeScreenTransition(2f);

    public static final ScreenTransition SLIDE=new SlideScreenTransition(1f);

    //constructors
    private ScreenTransitions(){}






}
