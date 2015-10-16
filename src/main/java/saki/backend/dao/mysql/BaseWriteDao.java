package saki.backend.dao.mysql;

import saki.backend.exception.DaoException;
import saki.backend.utils.BeanUtil;
import saki.backend.utils.ConstantUtil;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by liverliu on 14-7-3.
 */
public abstract class BaseWriteDao<T>{

    private final Logger LOGGER = LoggerFactory.getLogger(BaseWriteDao.class);

    @Autowired(required = true)
    protected SqlSessionTemplate sqlSessionTemplate;

    protected static final String DOT = ".";

    protected String sqlNameSpace = getSqlNameSpace();

    public String getSqlNameSpace() {
        return "";
    }

    public int insert(T entity) {
        long start = System.currentTimeMillis();
        String sqlExecutor = sqlNameSpace + DOT + ConstantUtil.INSERT;
        try {
            Map<String, Object> params = BeanUtil.toMap(entity);
            LOGGER.info(sqlExecutor + ":" + params.toString());
            return sqlSessionTemplate.insert(sqlExecutor, params);
        } catch (Exception ex) {
            throw new DaoException(String.format("添加对象出错！语句：%s", sqlExecutor), ex);
        } finally {
            long end = System.currentTimeMillis();
            LOGGER.info("use time:" + (end - start) + "ms");
        }
    }

    public int update(T entity) {
        long start = System.currentTimeMillis();
        String sqlExecutor = sqlNameSpace + DOT + ConstantUtil.UPDATE;
        try {
            Map<String, Object> params = BeanUtil.toMap(entity);
            LOGGER.info(sqlExecutor + ":" + params.toString());
            return sqlSessionTemplate.update(sqlExecutor, params);
        } catch (Exception ex) {
            throw new DaoException(String.format("更新对象出错！语句：%s", sqlExecutor), ex);
        } finally {
            long end = System.currentTimeMillis();
            LOGGER.info("use time:" + (end - start) + "ms");
        }
    }

}
