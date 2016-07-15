package saki.backend.dao.mysql;

import saki.backend.annotation.LogExeTime;
import saki.backend.annotation.LogInvoke;
import saki.backend.exception.DaoException;
import saki.backend.utils.BeanUtil;
import saki.backend.utils.ConstantUtil;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * Created by liverliu on 14-6-12.
 */
public abstract class BaseReadDao<T> {

    private final Logger LOGGER = LoggerFactory.getLogger(BaseReadDao.class);

    @Autowired(required = true)
    protected SqlSessionTemplate sqlSessionTemplate;

    protected static final String DOT = ".";

    protected String sqlNameSpace = getSqlNameSpace();

    public String getSqlNameSpace() {
        return "";
    }

    @LogExeTime
    @LogInvoke(params = {"query"})
    public <V extends T> V selectOne(T query) {
        String sqlExecutor = sqlNameSpace + DOT + ConstantUtil.SELECT;
        try {
            Map<String, Object> params = BeanUtil.toMap(query);
            return sqlSessionTemplate.selectOne(sqlExecutor, params);
        } catch (Exception ex) {
            throw new DaoException(String.format("查询一条记录出错！语句：%s", sqlExecutor), ex);
        }
    }

    @LogExeTime
    @LogInvoke(params = {"id"})
    public <V extends T> V selectById(String id) {
        Assert.notNull(id);
        String sqlExecutor = sqlNameSpace + DOT + ConstantUtil.SELECT_BY_ID;
        try {
            return sqlSessionTemplate.selectOne(sqlExecutor, id);
        } catch (Exception ex) {
            throw new DaoException(String.format("根据ID查询对象出错！语句：%s", sqlExecutor), ex);
        }
    }

    @LogExeTime
    @LogInvoke(params = {"query"})
    public <V extends T> List<V> selectList(T query) {
        String sqlExecutor = sqlNameSpace + DOT + ConstantUtil.SELECT;
        try {
            Map<String, Object> params = BeanUtil.toMap(query);
            return sqlSessionTemplate.selectList(sqlExecutor, params);
        } catch (Exception ex) {
            throw new DaoException(String.format("查询对象列表出错！语句：%s", sqlExecutor), ex);
        }
    }

    @LogExeTime
    @LogInvoke
    public <V extends T> List<V> selectList() {
        String sqlExecutor = sqlNameSpace + DOT + ConstantUtil.SELECT;
        try {
            return sqlSessionTemplate.selectList(sqlExecutor);
        } catch (Exception ex) {
            throw new DaoException(String.format("查询所有对象列表出错！语句：%s", sqlExecutor), ex);
        }
    }

    @LogExeTime
    @LogInvoke(params = {"query", "pageable"})
    public <V extends T> List<V> selectList(T query, Pageable pageable) {
        String sqlExecutor = sqlNameSpace + DOT + ConstantUtil.SELECT;
        try {
            Map<String, Object> params = getParams(query, pageable);
            return sqlSessionTemplate.selectList(sqlExecutor, params);
        } catch (Exception ex) {
            throw new DaoException(String.format("根据分页对象查询列表出错！语句:%s", sqlExecutor), ex);
        }
    }

    @LogExeTime
    @LogInvoke
    public int selectCount() {
        String sqlExecutor = sqlNameSpace + DOT + ConstantUtil.SELECT_COUNT;
        try {
            return sqlSessionTemplate.selectOne(sqlExecutor);
        } catch (Exception ex) {
            throw new DaoException(String.format("查询对象总数出错！语句：%s", sqlExecutor), ex);
        }
    }

    @LogExeTime
    @LogInvoke(params = {"query"})
    public int selectCount(T query) {
        String sqlExecutor = sqlNameSpace + DOT + ConstantUtil.SELECT_COUNT;
        try {
            Map<String, Object> params = BeanUtil.toMap(query);
            return sqlSessionTemplate.selectOne(sqlExecutor, params);
        } catch (Exception ex) {
            throw new DaoException(String.format("查询对象总数出错！语句：%s", sqlExecutor), ex);
        }
    }

    protected RowBounds getRowBounds(Pageable pageable) {
        RowBounds bounds = RowBounds.DEFAULT;
        if (null != pageable) {
            bounds = new RowBounds(pageable.getOffset(), pageable.getPageSize());
        }
        return bounds;
    }

    protected Map<String, Object> getParams(T query, Pageable pageable) {
        Map<String, Object> params = BeanUtil.toMap(query, getRowBounds(pageable));
        if (pageable != null && pageable.getSort() != null) {
            String sorting = pageable.getSort().toString();
            params.put("sorting", sorting.replace(":", ""));
        }
        return params;
    }

}
