package org.swamp.frontend.california.settings;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="themeView")
@SessionScoped
public class ThemeView implements Serializable {
    
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public void change(String color) {
        if(color.equals("green"))
            this.color = null;
        else
            this.color = "-" + color;
    }
    
    
}
