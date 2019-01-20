/*添加题目*/
function addQuestion(q) {alert("cxas");
  var html;
  var qType;
  var s;

  switch (q.type) {
  case '0': case 0: qType = "选择题"; break;
  case '1': case 1: qType = "判断题"; break;
  case '2': case 2: qType = "填空题"; break;
  case '3': case 3: qType = "问答题"; break;
  case '4': case 4: qType = "离线问题"; break;
  }
  if (q.type == '2' || q.type == 2) {
    var reg=new RegExp("{block}","g")
    s = q.describe.replace(reg, "______");
  }
  else
    s = q.describe;
  html  = '<div class="panel panel-default">';
  html += '  <div class="panel-heading">';
  html += '    <h3 class="panel-title">';
  html += '      <strong>'+qType+'</strong> '+q.name+'（'+q.score+'分）';
  html += '    </h3>';
  html += '  </div>';
  html += '  <div class="panel-body">';
  html += '    <p class="text-info">'+s+'</p>';
  // alert(q.type);
  if (q.type == "0") {
    html += '<p>A. '+q.choiceA+'</p>';
    html += '<p>B. '+q.choiceB+'</p>';
    html += '<p>C. '+q.choiceC+'</p>';
    html += '<p>D. '+q.choiceD+'</p>';
  }
  html += '  </div>';
  html += '  <div class="panel-footer" align="right">';
  html += '      <b class="textherf" onclick="delete_question('+q.qid+')">';
  html += '        <span class="glyphicon glyphicon-trash"/>删除';
  html += '      </b>&nbsp;&nbsp;&nbsp;&nbsp;';
  html += '    <b href="#modal-container-edit-question" data-toggle="modal" class="textherf" onclick="edit_question('+q.qid+')">';
  html += '      <span class="glyphicon glyphicon-edit"/>编辑';
  html += '    </b>';
  html += '  </div>';
  html += '</div>';
  $("#div_questions").append(html);
}