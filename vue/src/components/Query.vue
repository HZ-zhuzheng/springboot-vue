<template>
<div>


  <el-row class="query-body-row">
    <el-form ref="form" :model="sizeForm"  >
        <el-col :span="17" class="queryParam">
           <el-form-item label="">
              <el-select v-model="sizeForm.find" placeholder="模糊查询" class="query-select">
                <el-option label="精准查询" value="sure"></el-option>
                <el-option label="模糊查询" value="dim"></el-option>
              </el-select>
              <el-input v-model="sizeForm.name" size="1" placeholder="用户姓名" class="query-input"></el-input>
            </el-form-item>

            <el-form-item label="出生日期">
              <el-col :span="10">
                <el-date-picker unlink-panels
                  v-model="sizeForm.dates"
                  type="daterange"
                  value-format="yyyy-MM-dd"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期">
                </el-date-picker>
              </el-col>
              <el-col :span="5" class="query-radio">
                <el-form-item label="性别">
                   <el-radio-group v-model="sizeForm.sex">
                      <el-radio-button label="男"></el-radio-button>
                      <el-radio-button label="女"></el-radio-button>
                    </el-radio-group>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="年龄">
                   <el-input-number v-model="sizeForm.agestart" class="query-input-number" controls-position="right" :min="0" :max="num1Max" ></el-input-number>
                   -
                   <el-input-number v-model="sizeForm.ageend" class="query-input-number" @change="handleChange" controls-position="right" :min="0" :max="150" ></el-input-number>
                </el-form-item>
              </el-col>
            </el-form-item>
            <el-form-item label="城市">
              <el-col :span="2">
                <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange" style="float:left">全选</el-checkbox>
              </el-col>
              <el-col :span="20">
                <el-checkbox-group v-model="sizeForm.checkedCities" @change="handleCheckedCitiesChange" style="float:left">
                  <el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>
                </el-checkbox-group>
              </el-col>
            </el-form-item>
        </el-col>
        <el-col :span="1"><el-divider direction="vertical" class="yquer-divider"></el-divider></el-col>
        <el-col :span="6" class="queryBtn">
          <el-button type="primary" class="queryBtn-btn" :loading="queryBtnIsLoading" @click="toQueryBtn">{{queryBtnText}}</el-button>
          <el-button type="primary" class="queryBtn-btn" :loading="queryBtnIsLoading" @click="toCancelBtn" >取消重置</el-button>
        </el-col>
    </el-form>
     
  </el-row>
</div>
</template>

<script>
  const cityOptions = ['上海', '北京', '广州', '深圳']
  export default {
    components:{},
    data() {
      return {
        sizeForm: {
          name: '',
          find: 'dim',
          dates: [],
          agestart: 0,
          ageend: 0,
          sex: '',
          checkedCities: [],
          pageNo:1,
          pageSize:10
        },
        num1Max: 1,
        checkAll: false,
        cities: cityOptions,
        isIndeterminate: true,
        queryBtnText: '查询',
        queryBtnIsLoading: false
      };
    },
     watch:{
     },
    methods: {
      handleChange(value){ // 年龄范围开始 控制 小于等于 后者

          this.num1Max = parseInt(value);
      },
      handleCheckAllChange(val) {

        this.sizeForm.checkedCities = val ? cityOptions : [];
        this.isIndeterminate = false;
      },
      handleCheckedCitiesChange(value) {

        let checkedCount = value.length;
        this.checkAll = checkedCount === this.cities.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
      },
      toCancelBtn(){ //重置查询选项

        this.sizeForm.name = ''
        this.sizeForm.find = 'dim'
        this.sizeForm.dates = []
        this.sizeForm.agestart = 0
        this.sizeForm.ageend = 0
        this.sizeForm.sex = ''
        this.sizeForm.checkedCities = []
        this.sizeForm.pageNo = 1
        this.sizeForm.pageSize = 10
        this.num1Max = 1
        this.checkAll = false
        this.isIndeterminate = true
      },
      toQueryBtn(){ // 点击查询

        if(!this.queryBtnIsLoading){
          this.queryBtnText = '查询中';
          this.queryBtnIsLoading = true;
          var self = this // 解决setTimeout不能直接使用this
          var url =this.$store.state.countRUL+"selectCountCon"

          this.sizeForm.pageNo = 1
          this.sizeForm.pageSize = 10
          this.$store.state.sizeForm = this.sizeForm // 缓存查询条件
          // console.log(JSON.stringify(this.sizeForm))

          this.$ajax.post(url,this.sizeForm).then(function(response){
              self.$store.state.selectList = response.data //更新查询数据
              self.queryBtnText = '查询';
              self.queryBtnIsLoading = false;
              self.$store.state.currentPage = 1
          }).catch(function(error){
              alert("查询失败："+JSON.stringify(error))
              self.queryBtnText = '查询';
              self.queryBtnIsLoading = false;
          })
        }
      },
    },
    computed:{
    }
  };

</script>
<style scoped>
  .queryBtn-btn{
    width: 11em;
    height: 4em;
    margin-top: 4em;
  }
  .yquer-divider{
    height: 190px;
    width: 2px;
  }
  .query-body-row{
    height: 190px;
    background-color: #29b7cb;
    border-radius: 0.3em;
  }
  .query-input-number {
    width: 7em;
  }

  .query-input{
    width: 85%;
    /* height: 32px; */
  }
  .query-select{
    width: 12%;
  }
  .queryParam{
    /* background-color:blue; */
    padding: 10px;
    text-align: left;
  }
</style>