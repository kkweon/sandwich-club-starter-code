package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject o = new JSONObject(json);
            JSONObject nameObject = o.getJSONObject("name");

            return new Sandwich()
                    .setMainName(nameObject.getString("mainName").trim())
                    .setAlsoKnownAs(fromJSONArray(nameObject.getJSONArray("alsoKnownAs")))
                    .setPlaceOfOrigin(o.getString("placeOfOrigin").trim())
                    .setDescription(o.getString("description").trim())
                    .setImage(o.getString("image").trim())
                    .setIngredients(fromJSONArray(o.getJSONArray("ingredients")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> fromJSONArray(JSONArray array) throws JSONException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < array.length(); ++i) {
            result.add(array.getString(i).trim());
        }
        return result;
    }
}
