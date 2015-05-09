package linkedincoursera.model;

import java.util.List;

/**
 * Created by harsh on 4/25/15.
 */
public class QuesSof {
    private List<QuestionCountSOF> items;
    private boolean hasMore;
    private Integer quotaMax;
    private Integer quotaRemaining;

    public List<QuestionCountSOF> getItems() {
        return items;
    }

    public void setItems(List<QuestionCountSOF> items) {
        this.items = items;
    }


    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(Integer quotaMax) {
        this.quotaMax = quotaMax;
    }

    public Integer getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(Integer quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }
}
