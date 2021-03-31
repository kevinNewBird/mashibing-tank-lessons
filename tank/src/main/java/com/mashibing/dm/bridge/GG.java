package com.mashibing.dm.bridge;

/***********************
 * @Description: 男朋友 <BR>
 * @author: zhao.song
 * @since: 2021/4/1 0:38
 * @version: 1.0
 ***********************/
public class GG {

    /**
     * Description: 追求美美 <BR>
     *
     * @param mm:
     * @return
     * @author zhao.song    2021/4/1 0:40
     */
    public void chase(MM mm) {
        Gift gift = new WarmGift(new Book());
        give(mm, gift);
    }

    private void give(MM mm, Gift gift) {
        System.out.println(gift + "gived!");
    }
}
