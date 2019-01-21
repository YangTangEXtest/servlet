function getjson() {
    var radio = new Array();//存放答案的数组
    
    var choice_answer = $(".choice_answer");//获取到hidden input对象
    
    for (var i = 1; i <= 3; i++) {
        
        var radio_name = new String("radio_" + i);//获取到单选对象
        
        //获取到学生选取的单选的值赋给radio数组
        radio[i - 1] = $('input:radio[name=' + radio_name + ']:checked').val()
    }
    //将获取到的radio的值赋值给hidden input
    var j=0;
    choice_answer.each(function(){
        alert("each");
        $(this).val(radio[j]);
        alert("j的值为："+j);
        alert("赋的值为："+$(this).val());
        j++;
    });
    
    $("form").submit();
    alert("提交了！");
    
}