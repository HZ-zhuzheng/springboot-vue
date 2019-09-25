import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const state = {
    count: 0,
    selectList:[],     //查询列表
    isDrawer:false,     //drawer的标识
    addFrom:{
          id:'',
          number: '',
          name: '',
          city: '',
          birth: '',
          sex: '',
          age: 1,
          place: ''
    },  //添加表单数据
    isAddTypes:false,   // 表单操作类型 false为添加，ture为修改
    countRUL:'http://localhost:8081/frame/count/',  // 后台访问url
    sizeForm:{
        name: '',
        find: 'dim',
        dates: [],
        agestart: 0,
        ageend: 0,
        sex: '',
        checkedCities: [],
        pageNo:1,
        pageSize:10
    },  //查询数据对象
    queryChooseList:[], //列表选中对象数组
    currentPage:0  //  list页码
}
const mutations={
    addFromchangNull(state){    // 清空表单数据
        state.addFrom.id = ''
        state.addFrom.number = ''
        state.addFrom.name =''
        state.addFrom.city =''
        state.addFrom.birth= ''
        state.addFrom.sex= ''
        state.addFrom.age= 1,
        state.addFrom.place= ''
    }
}

export default new Vuex.Store({
    state,
    mutations
})