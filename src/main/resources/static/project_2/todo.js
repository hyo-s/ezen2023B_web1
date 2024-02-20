console.log('todo.js 실행')



function todoCreate(){
    console.log('todoCreate 함수 실행')
    let content = document.querySelector("#content").value;
    console.log(content);

    //객체화 할 필요 없음

      $.ajax({ // ajax 쓸려면 html에 jQuery 포함 되어야함.
                url : '/todo/create',
                method : 'POST',
                data : {content : content}, // 객체 없이 바로 객체로 만들기
                // contentType : application/json 이런식으로 변환도 가능함. 지금은 폼과 동일하게 전송되는 일.(기본값이 form)
                success : function ( result ){ //함수명은 생략가능. Responsebody의 return값이 success의 result 로 들어온다.
                    console.log(result);
                    // 4. 결과
                    if(result){
                        alert('할 일 등록 성공');
                        location.href = '/todo';
                    }else{
                        alert('할 일 등록 실패');
                    }
                }
            });
}

function tododelete(id){
    console.log('tododelete 함수 실행')
    console.log(id);


//    객체화 할 필요 없음

      $.ajax({ // ajax 쓸려면 html에 jQuery 포함 되어야함.
                url : '/todo/delete',
                method : 'POST',
                data : {id : id}, // 객체 없이 바로 객체로 만들기
                // contentType : application/json 이런식으로 변환도 가능함. 지금은 폼과 동일하게 전송되는 일.(기본값이 form)
                success : function ( result ){ //함수명은 생략가능. Responsebody의 return값이 success의 result 로 들어온다.
                    console.log(result);
                    // 4. 결과
                    if(result){
                        alert('할 일 삭제 성공');
                        location.href = '/todo';
                    }else{
                        alert('할 일 삭제 실패');
                    }
                }
            });
}

function success(id){
    console.log("할일 완료 함수")
    console.log(id);

     $.ajax({ // ajax 쓸려면 html에 jQuery 포함 되어야함.
                    url : '/todo/success',
                    method : 'POST',
                    data : {id : id}, // 객체 없이 바로 객체로 만들기
                    // contentType : application/json 이런식으로 변환도 가능함. 지금은 폼과 동일하게 전송되는 일.(기본값이 form)
                    success : function ( result ){ //함수명은 생략가능. Responsebody의 return값이 success의 result 로 들어온다.
                        console.log(result);
                        // 4. 결과
                        if(result){
                            alert('할 일 완료 성공');
                             location.href = '/todo';
//                            $(".todo"+id).addClass("line")

                        }else{
                            alert('할 일 완료 실패');
                        }
                    }
                });
}