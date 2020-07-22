package org.restaurant.models;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
@Data
public class UserRequest {
    public String name;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    public String mealName;
    private Meal meal;
    public UserRequest() {
    }
    public UserRequest(String name, String mealName) {
        this.name = name;
        this.mealName = mealName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMealName() {
        return mealName;
    }
}