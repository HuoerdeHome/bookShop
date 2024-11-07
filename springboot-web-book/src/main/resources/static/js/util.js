// 处理后端传递过来的树形数据children为空，下拉框显示暂无数据的问题
function getTreeData(data = []) {
    
    if (data === []) {
        return false
    }

    for (let i = 0; i < data.length; i++) {
        if (data[i].children === undefined || data[i].children.length === 0) { /// 改成这样判断条件
            // children若为空数组，则将children设为undefined
            data[i].children = undefined
        } else {
            // children若不为空数组，则继续 递归调用 本方法
            this.getTreeData(data[i].children)
        }
    }
    
    return data
}