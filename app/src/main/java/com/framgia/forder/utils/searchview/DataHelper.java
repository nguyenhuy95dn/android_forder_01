package com.framgia.forder.utils.searchview;

/**
 * Copyright (C) 2015 Ari C.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.util.Log;
import android.widget.Filter;
import com.framgia.forder.data.model.DataSuggest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class DataHelper {
    private static final String TAG = "DataHelper";
    private List<DataSuggest> mProducts = new ArrayList<>(
            Arrays.asList(new DataSuggest("Com tấm"), new DataSuggest("Nước Chanh"),
                    new DataSuggest("Sinh Tố"), new DataSuggest("Mì Quảng"),
                    new DataSuggest("sữa coffe"), new DataSuggest("Trà sữa alisan"),
                    new DataSuggest("Trà sữa Gong Cha"), new DataSuggest("Mì Quảng Ếch"),
                    new DataSuggest("Chè Đậu Thập Cẩm"), new DataSuggest("Dừa Bến Tre"),
                    new DataSuggest("Rau Câu Flan Trái Dừa"), new DataSuggest("Nước sấu-15"),
                    new DataSuggest("Mì Que"), new DataSuggest("Trà sữa BoBaPop"),
                    new DataSuggest("Trà sữa trân châu đen"), new DataSuggest("Cà Rốt Ép"),
                    new DataSuggest("Chè Đậu Xanh Đánh"), new DataSuggest("Dâu Xay Sữa Chua")));

    public void setDataSuggest(List<DataSuggest> products) {
        mProducts = products;
    }

    public void resetSuggestionsHistory() {
        for (DataSuggest dataSuggest : mProducts) {
            dataSuggest.setHistory(false);
        }
    }

    public void findSuggestions(Context context, String query, final int limit,
            final long simulatedDelay, final OnFindSuggestionsListener listener) {
        new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                try {
                    Thread.sleep(simulatedDelay);
                } catch (Exception e) {
                    Log.e(TAG, "performFiltering: ", e);
                }
                resetSuggestionsHistory();
                List<DataSuggest> suggestionList = new ArrayList<>();
                if (!(constraint == null || constraint.length() == 0)) {
                    for (DataSuggest suggestion : mProducts) {
                        if (suggestion.getBody()
                                .toUpperCase(Locale.getDefault())
                                .startsWith(
                                        constraint.toString().toUpperCase(Locale.getDefault()))) {

                            suggestionList.add(suggestion);
                            if (limit != -1 && suggestionList.size() == limit) {
                                break;
                            }
                        }
                    }
                }

                FilterResults results = new FilterResults();
                Collections.sort(suggestionList, new Comparator<DataSuggest>() {
                    @Override
                    public int compare(DataSuggest lhs, DataSuggest rhs) {
                        return lhs.isHistory() ? -1 : 0;
                    }
                });
                results.values = suggestionList;
                results.count = suggestionList.size();

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (listener != null) {
                    listener.onResults((List<DataSuggest>) results.values);
                }
            }
        }.filter(query);
    }

    public interface OnFindSuggestionsListener {
        void onResults(List<DataSuggest> results);
    }
}
