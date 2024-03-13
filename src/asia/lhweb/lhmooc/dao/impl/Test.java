package asia.lhweb.lhmooc.dao.impl;

import asia.lhweb.lhmooc.dao.BasicDAO;
import asia.lhweb.lhmooc.dao.TestDao;

/**
 * @author :罗汉
 * @date : 2024/3/12
 */
public class Test extends BasicDAO  implements TestDao {
    @Override
    public void myTest() {
        selectAll(new Test());

    }
}
