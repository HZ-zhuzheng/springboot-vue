<template>
  <div class="wrapper">
    <el-form ref="form" :model="form" label-width="80px">
  <el-form-item label="用户编号">
    <el-input v-model="form.number"></el-input>
  </el-form-item>
  <el-form-item label="用户名称">
    <el-input v-model="form.name"></el-input>
  </el-form-item>
  <el-form-item label="所在城市">
    <el-select v-model="form.city" placeholder="请选择所在城市">
      <el-option label="上海" value="上海"></el-option>
      <el-option label="北京" value="北京"></el-option>
      <el-option label="广州" value="广州"></el-option>
      <el-option label="深圳" value="深圳"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="出生日期">
    <el-col :span="11">
      <el-date-picker type="date" placeholder="选择出生日期" v-model="form.birth" style="width: 100%;" value-format="yyyy-MM-dd"></el-date-picker>
    </el-col>
  </el-form-item>
  <el-form-item label="用户年龄">
      <el-input-number v-model="form.age" :min="1" :max="150"></el-input-number>
  </el-form-item>
  <el-form-item label="用户性别">
    <el-radio-group v-model="form.sex">
      <el-radio label="男"></el-radio>
      <el-radio label="女"></el-radio>
    </el-radio-group>
  </el-form-item>
  <el-form-item label="所在地址">
    <el-input type="textarea" v-model="form.place"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" v-if="!isAddTypes" @click="onToAdd">立即创建</el-button>
    <el-button type="primary" v-if="isAddTypes" @click="onToUpdate">提交修改</el-button>
    <el-button @click="onCancel">取消</el-button>
  </el-form-item>
</el-form>
           
  </div>
</template>

<script>
export default {
  components:{},
  props:{},
  data(){
    return {
        form:this.$store.state.addFrom ,
        isAddTypes:this.$store.state.isAddTypes
    }
  },
  watch:{
    '$store.state.addFrom':function(){
        this.form = this.$store.state.addFrom
      //  console.log("addFrom-Addfrom")
    },
    '$store.state.isAddTypes':function(){
        this.isAddTypes = this.$store.state.isAddTypes
      //  console.log("addFrom-isAddTypes")
    }
  },
  computed:{},
  methods:{
     onToAdd() { // 添加记录
        var url = this.$store.state.countRUL+'addCountCon'
        var self = this
        this.$ajax.post(url,this.form).then(function (response) {  //请求成功
          console.log("请求成功"+response);
          self.$store.commit('addFromchangNull')  //调用addFrom置空方法
        }).catch(function (error) { //请求失败
          console.log("请求失败"+error);
        });
      },

      onToUpdate(){ // 修改记录
        var url =this.$store.state.countRUL+'updateCountCon'
        this.$ajax.post(url,this.form).then(function (response) { //请求成功
          alert(response.data)
        }).catch(function (error) { //请求失败
          alert("请求失败"+error);
        });
      },

      onCancel(){ //点击取消按钮
        // console.log("onCancel")
        this.$store.state.isDrawer = false
        this.$forceUpdate(); 
      }
  },
  created(){},
  mounted(){
  }
}
</script>
<style scoped>

</style>