<template>
  <div class="wrapper">
    <el-row style="width:100%">
      <el-col :span="24"><div class="grid-content bg-purple main-query"><Query></Query></div></el-col>
    </el-row>
    <el-row>
      <el-col :span="8" class="main-cent-row">
          <el-button  @click="onAdd" type="primary" style="margin-left: 16px;">添加用户</el-button>
          <el-button  @click="onUpdate" type="primary" style="margin-left: 16px;">修改用户</el-button>
          <el-button  @click="onDelete"  type="primary" style="margin-left: 16px;">删除用户</el-button>
      </el-col>
    </el-row>
     <el-row>
         <el-col :span="24"><List></List></el-col>
    </el-row>

      <el-drawer
        title="用户信息管理"
        :visible.sync="drawer"
        :before-close="handleClose"
        :direction="direction">
            <AddForm></AddForm>
      </el-drawer>
  </div>

  
</template>

<script>
import Query from '../components/Query'
import List from '../components/List'
import AddForm from '../components/AddForm'
export default {
  components:{ Query, List, AddForm},
  props:{},
  data(){
    return {
        drawer: this.$store.state.isDrawer,
        direction: 'rtl',
        drawerOpenUpdate:false

      }
  },
  watch:{
    '$store.state.isDrawer':function(){ // 监听抽屉状态数据
      this.drawer = this.$store.state.isDrawer
      // console.log("Main-isDrawer")
    }
  },
  computed:{},
  methods:{
      handleClose(){ //抽屉关闭前处理
      //  console.log(JSON.stringify(this.$store.state.addFrom))
        this.$store.commit('addFromchangNull')  //调用addFrom置空方法
        this.$store.state.isDrawer = false
        this.drawer = false
        // this.$forceUpdate(); 
        console.log("Main-handleClose")

        // 刷新列表
       this.flushList()
      },

      onAdd(){  //添加记录
        this.drawer = true
        this.$store.state.isAddTypes = false
      },

      onDelete(){ //删除记录
        if(this.$store.state.queryChooseList.length != 0){
          var url =this.$store.state.countRUL+"deleteCountCon"
          var arrayId = [];
          this.$store.state.queryChooseList.forEach(element => {
            arrayId.push(element.id);
          });

          var postData = { // 组装请求数据对象
            deleteID : arrayId
          }
          var self = this
          // console.log(JSON.stringify(postData));
          this.$ajax.post(url, postData).then(function(response){
            alert(response.data);
            self.flushList() //刷新列表
          }).catch(function(error){
             alert("请求失败:"+JSON.stringify(error));
          })

        }else{
          alert("您未勾选数据！");
        }
      },

      onUpdate(){ //修改选中的记录
         if (this.$store.state.queryChooseList.length == 0) {
            alert("您未勾选数据！");
            return 0;
         }
         if (this.$store.state.queryChooseList.length > 1) {
           alert("您勾选了多条数据，只能同时修改一条数据");
            return 0;
         }
        var list0 = this.$store.state.queryChooseList[0]

        // 缓存勾选的数据 在表单上显示
        this.$store.state.addFrom.id = list0.id
        this.$store.state.addFrom.number = list0.number
        this.$store.state.addFrom.name = list0.name
        this.$store.state.addFrom.city = list0.city
        this.$store.state.addFrom.birth = list0.birthStr
        this.$store.state.addFrom.sex = list0.sex
        this.$store.state.addFrom.age = list0.age
        this.$store.state.addFrom.place = list0.place
        this.$store.state.isAddTypes = true
        this.$store.state.isDrawer = true
      },
      flushList(){  //列表刷新
        // 刷新列表
        var self = this
        var url =this.$store.state.countRUL+"selectCountCon"
        this.$ajax.post(url,this.$store.state.sizeForm).then(function(response){
          // console.log(JSON.stringify(response))
          self.$store.state.selectList = response.data //更新查询数据
          self.$store.state.currentPage = 1
        }).catch(function(error){
          alert("查询失败："+JSON.stringify(error))
        })
      }
  },
  created(){},
  mounted(){}
}
</script>
<style>
    .main-cent-row{
        height: 3.3em;
        /* background-color: aqua; */
    }
  .main-query{
    height: 190px;
    background-color: white;
    
  }
 
</style>