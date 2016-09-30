#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <math.h>
#include <sys/time.h>

pthread_t thre[100];   //存储线程id
  
 
typedef struct {
    int start, end, n, k;
    double p;        //每个线程计算的值
}ARGS;

  
ARGS args[1000];    //每个线程对应一个结构体 


/**
 *单线程计算
 *
 */
void* part(void *arg){

     int start = ((ARGS *) arg) ->  start;
     int end = ((ARGS *) arg) -> end;
     int n = ((ARGS *) arg) -> n;
     int i; 
     double temp = 0.0;
   

 for(i = start; i <= end; i++){
    temp += 4 / (1 + pow((i  +0.5) / n, 2.0));
 }
  
  ((ARGS *)arg) -> p = temp;
}


/**
 *确认每个线程均已结束
 *
 */
void  check_end(int thr){
  int i;
  for(i = 0;i < thr;i++)
   pthread_join(thre[i], NULL);  
}

/**
 *多线程计算pi值
 *
 */
double pi(int n,int thr){

  int times = n / thr;                                       //根据线程数划分n
  int i,ret;
  
  int num = 0;
  for(i = 0; i < thr; i++){

    args[i].start = 1 + num, args[i].end = times + num,args[i].n = n;

    ret = pthread_create(&thre[i], NULL, part,(void*)&args[i]);      // 开启线程，将结构体参数传入
    if(ret != 0){
      printf("Create pthread error!\n");
      exit(1);
    }

    num += times;                                                            //将下一组加入线程
  }
   
  if(n % thr != 0){                                                              //将余数加入线程
    
    args[i].start = 1 + num, args[i].end = n,args[i].n = n;

    ret = pthread_create(&thre[thr], NULL, part, (void*)&args[thr]);
    if(ret != 0){
      printf("Create pthread error!\n");
      exit(1);
    } 
    i++;
    check_end(thr+1);                                                          //确认所有线程均已结束

  }else{

    check_end(thr);
  }
  
  double temp = 0.0;
  int k;
  for(k = 0; k < i; k++){                  //所有线程累加求和
    temp += args[k].p;
  }
  
  return temp/n;
}



int main(int argc, char** argv){


  while(1){
   int n,thre_num;
   printf("输入计算次数n： ");
   scanf("%d", &n);
   printf("输入开启的线程的数量:  ");
   scanf("%d",&thre_num);
   struct timeval begin, stop;              //时间测量
   gettimeofday(&begin, 0);

   printf("%lf\n", pi(n,thre_num));          //计算pi值

   gettimeofday(&stop, 0);
   double timeuse = 1000000*(stop.tv_sec - begin.tv_sec) + stop.tv_usec - begin.tv_usec;   
   printf("Time: %lf ms \n",timeuse/=1000);  //除以1000则进行毫秒计时

  }
  
 
  return 0;
}
