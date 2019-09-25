<template>
    <div class="grid-content bg-purple">
        <el-table 
        border class="list-table"
        ref="multipleTable"
        :data="tableData"
        height="300"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
            <el-table-column
                type="selection"
                width="55">
            </el-table-column>
             <el-table-column
                prop="name"
                label="姓名"
                width="120">
                <template slot-scope="scope">{{ scope.row.name }}</template>
            </el-table-column>
            <el-table-column
                prop="number"
                label="编号"
                width="120">
            </el-table-column>
            <el-table-column
                prop="birthStr"
                label="出生日期"
                width="120">
            </el-table-column>
           
             <el-table-column
                prop="age"
                label="年龄"
                width="120">
            </el-table-column>
            <el-table-column
                prop="sex"
                label="性别"
                width="120">
            </el-table-column>
            <el-table-column
                prop="city"
                label="城市"
                width="120">
            </el-table-column>
            <el-table-column
                prop="place"
                label="出生地址"
                show-overflow-tooltip>
            </el-table-column>
        </el-table>
       <el-pagination
        :hide-on-single-page="hienpageValue"
        :page-size="10"
        :total="dataNum"
        :current-page="currentPage"
        @current-change="changePageNum"
        @prev-click="changePageNum"
        @next-click="changePageNum"
        layout="prev, pager, next">
      </el-pagination>
    </div>
</template>

<script>
export default {
  components:{},
  props:{},
  data(){
    return {
        tableData: [],  //表格数据
        multipleSelection: [],  //勾选的数据
        hienpageValue:false,  // 是否隐藏分页样式
        dataNum:0,
        currentPage:1  //  初始页码
    }
  },
  watch:{
    '$store.state.selectList':function(){  //监听查询列表数据变化
      this.tableData = this.$store.state.selectList
      // console.log("selectList-List")

       // 查询总记录数
      var url =this.$store.state.countRUL+"selectCountTotalCon"
      var lesf = this
      this.$ajax.post(url,this.$store.state.sizeForm).then(function(response){
          lesf.dataNum  = response.data.countNum
      }).catch(function(error){
          alert("请求查询记录总数 失败："+JSON.stringify(error))
      })

      if ( this.tableData.length <= 10 ) {
        this.hienpageValue = true
      }else{
        this.hienpageValue = false
      }
    },
    '$store.state.currentPage':function(){ //监听页码变化 实现列表刷新，页码同步刷新
      this.currentPage = this.$store.state.currentPage
    }
  },
  computed:{},
  methods:{

      toggleSelection(rows) {
        if (rows) {
          rows.forEach(row => {
            this.$refs.multipleTable.toggleRowSelection(row);
          });
        } else {
          this.$refs.multipleTable.clearSelection();
        }
      },

      handleSelectionChange(val) { // 勾选触发事件
        this.multipleSelection = val;
        this.$store.state.queryChooseList = val;
        // console.log("勾选")
      },

      changePageNum(value){ //页码变化事件
        //  console.log("lsit:"+JSON.stringify(value))
        this.$store.state.currentPage = value // 保存当前点击的页码
         var url =this.$store.state.countRUL+"selectCountCon"
         var sizeForm = this.$store.state.sizeForm
         sizeForm.pageNo = value
         var lef = this
        this.$ajax.post(url,this.$store.state.sizeForm).then(function(response){
            lef.tableData = response.data
        }).catch(function(error){
            alert("查询记录失败："+JSON.stringify(error))
        })
     }

  },
  created(){},
  mounted(){
  }
}
</script>
<style scoped>
.list-pagination{
    margin-top: 2em;
}
</style>