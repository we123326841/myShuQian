//
//  ViewController.m
//  IOSRequestDemo
//
//  Created by 王浩 on 16/10/17.
//  Copyright © 2016年 tencent. All rights reserved.
//

#import "ViewController.h"
#import "AFNetworking.h"
#import "NSObject+ALiHUD.h"
@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
//    NSLog(@"%@",@"得得的请问");
//     NSLog(@"%@",[NSThread currentThread]);
//    
//    NSThread *thread = [[NSThread alloc]initWithTarget:self selector:@selector(downloadImage) object:nil];
//    thread.name = @"什么鸡巴毛";
//    [thread start];
//    [self showProgress:45];
}



-(void)downloadImage{
     NSLog(@"你麻痹:%@",[NSThread currentThread]);
}


-(void)touchesBegan:(NSSet<UITouch *> *)touches withEvent:(UIEvent *)event{
  
//    [self postYuans];
    [self postAction];
}


-(void)postAction{
//    POST /MJServer/login HTTP/1.1
//Host: 192.168.1.17:8080
//    Content-Type: application/x-www-form-urlencoded
//Connection: keep-alive
//Accept: */*
//          User-Agent: IOSRequestDemo/1 CFNetwork/808.0.2 Darwin/16.0.0
//          Accept-Language: zh-cn
//          Accept-Encoding: gzip, deflate
//          Content-Length: 20
//          
//          username=123&pwd=123
    
          
    
    
    
    NSString *str = @"http://192.168.1.103:8080/MJServer/login";
   ;
    NSMutableURLRequest * request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:str]];
    request.HTTPMethod = @"POST";
    NSDictionary *dict = @{@"shop_id":@"123213",@"shop_name":@"我的商店头",@"user_id":@"124",@"www":@3,@"ppp":@(YES),@"opopop":@(NO),@"lll":@1};
    
    NSData *data = [NSJSONSerialization dataWithJSONObject:dict options:NSJSONWritingPrettyPrinted error:nil];
    
//    NSLog(@"%@",[[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding]);

    NSString * bodyStr = [NSString stringWithFormat:@"username=%@&pwd=123",[[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding]];
    
    request.HTTPBody = [bodyStr dataUsingEncoding:NSUTF8StringEncoding];
    [NSURLConnection sendAsynchronousRequest:request queue:[[NSOperationQueue alloc] init]completionHandler:^(NSURLResponse * _Nullable response, NSData * _Nullable data, NSError * _Nullable connectionError) {
        NSLog(@"%@",[NSThread currentThread] );
        
        NSString *str = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
        NSLog(@"%@",response);
        
        NSLog(@"%@",str);
    }];
    
    
    //   NSData * dataH = [NSURLConnection sendSynchronousRequest:request returningResponse:nil error:nil];
    //    NSLog(@"%@", [[NSString alloc] initWithData:dataH encoding:NSUTF8StringEncoding]);
    NSLog(@"end......");
}


-(void)postAfnAction{
    AFHTTPSessionManager * manager = [AFHTTPSessionManager manager];
    manager.responseSerializer = [AFJSONResponseSerializer new];
    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json", nil];
    
    [manager POST:@"http://192.168.91.103:8080/MJServer/login" parameters:@{@"username":@"123",@"pwd":@"123"} progress:^(NSProgress * _Nonnull uploadProgress) {
        
    } success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSLog(@"成功--%@",responseObject);
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSLog(@"失败%@",error);
    }];
}


-(void)getAction{
//    GET /MJServer/login?username=123&pwd=123 HTTP/1.1
//Host: 192.168.1.17:8080
//Accept: */*
//          User-Agent: IOSRequestDemo/1 CFNetwork/808.0.2 Darwin/16.0.0
//          Accept-Language: zh-cn
//          Accept-Encoding: gzip, deflate
//          Connection: keep-alive
    
          
          
          
    
    NSString *str = @"http://192.168.1.17:8080/MJServer/login?username=123&pwd=123";
    NSString * encodeStr = [str stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
    NSLog(@"encode==%@",encodeStr);
    NSMutableURLRequest * request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:encodeStr]];
    request.HTTPMethod = @"GET";
    [NSURLConnection sendAsynchronousRequest:request queue:[[NSOperationQueue alloc] init]completionHandler:^(NSURLResponse * _Nullable response, NSData * _Nullable data, NSError * _Nullable connectionError) {
        NSLog(@"%@",[NSThread currentThread] );
        
        NSString *str = [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
        NSLog(@"%@",response);
        
        NSLog(@"%@",str);
    }];
    
    
    //   NSData * dataH = [NSURLConnection sendSynchronousRequest:request returningResponse:nil error:nil];
    //    NSLog(@"%@", [[NSString alloc] initWithData:dataH encoding:NSUTF8StringEncoding]);
    NSLog(@"end......");
}




-(void)postJsonAction{
    NSString *str = @"http://192.168.1.103:8080/MJServer/order";
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:[NSURL URLWithString:str]];
    request.HTTPMethod = @"POST";
    [request setValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    NSDictionary *dict = @{@"shop_id":@"123213",@"shop_name":@"我的商店头",@"user_id":@"124",@"www":@3,@"ppp":@(YES),@"opopop":@(NO),@"lll":@1};
    
    NSData *data = [NSJSONSerialization dataWithJSONObject:dict options:NSJSONWritingPrettyPrinted error:nil];
    request.HTTPBody = data;
   NSLog(@"%@",[[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding]);
    [NSURLConnection sendAsynchronousRequest:request queue:[NSOperationQueue mainQueue] completionHandler:^(NSURLResponse * _Nullable response, NSData * _Nullable data, NSError * _Nullable connectionError) {
        NSLog(@"%@",[[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding]);
    }];
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


-(void)postUpdate{
    AFHTTPSessionManager * manager = [AFHTTPSessionManager manager];
    manager.responseSerializer = [AFJSONResponseSerializer new];
    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"text/html", nil];
    [manager POST:@"http://ios1.phl5b.org/upload/uploadFiles" parameters:@{@"CGISESSID":@"t+RIX8BYzodZ5w6vLflngGt7D9RMo0d43LTkcEMVYLCXMyJupgbzNA=="} constructingBodyWithBlock:^(id<AFMultipartFormData>  _Nonnull formData) {
       NSData *data  = UIImagePNGRepresentation([UIImage imageNamed:@"MyImage"]);
       [formData appendPartWithFileData:data name:@"idCard0" fileName:@"nimabi.png" mimeType:@"image/jpg"];
    } progress:^(NSProgress * _Nonnull uploadProgress) {
        NSLog(@"进度%@",uploadProgress);
        
    } success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSLog(@"成功%@",responseObject);
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSLog(@"失败%@",error);
    }];
}



-(void)postUpdateMj{
    
    
    
    AFHTTPSessionManager * manager = [AFHTTPSessionManager manager];
    manager.responseSerializer = [AFJSONResponseSerializer new];
    manager.responseSerializer.acceptableContentTypes = [NSSet setWithObjects:@"application/json", nil];
    [manager POST:@"http://192.168.1.12:8080/MJServer/upload" parameters:@{@"username":@"jibamao"} constructingBodyWithBlock:^(id<AFMultipartFormData>  _Nonnull formData) {
        NSData *data  = UIImagePNGRepresentation([UIImage imageNamed:@"MyImage"]);
        [formData appendPartWithFileData:data name:@"file" fileName:@"nimabi.png" mimeType:@"image/jpg"];
    } progress:^(NSProgress * _Nonnull uploadProgress) {
        NSLog(@"进度%@",uploadProgress);
        
    } success:^(NSURLSessionDataTask * _Nonnull task, id  _Nullable responseObject) {
        NSLog(@"成功%@",responseObject);
    } failure:^(NSURLSessionDataTask * _Nullable task, NSError * _Nonnull error) {
        NSLog(@"失败%@",error);
    }];
}


-(void)postYuans{
    NSString *urlStr = [NSString stringWithFormat:@"http://localhost:8080/MJServer/login?username=123&pwd=123"];
    NSURL *url = [NSURL URLWithString:urlStr];
    
    // 创建一个请求
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    NSLog(@"begin---");
    
    // 发送一个同步请求(在主线程发送请求)
    NSData *data = [NSURLConnection sendSynchronousRequest:request returningResponse:nil error:nil];
    
    // 解析服务器返回的JSON数据
    NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:nil];
    NSLog(@"我他妈返回:%@", [[NSString alloc] initWithData:data  encoding:NSUTF8StringEncoding] );
    NSLog(@"%@",dict);
//    NSString *error = dict[@"error"];
//    if (error) {
//        // {"error":"用户名不存在"}
//        // {"error":"密码不正确"}
//       // [MBProgressHUD showError:error];
//    } else {
//        // {"success":"登录成功"}
//        NSString *success = dict[@"success"];
//       // [MBProgressHUD showSuccess:success];
//    }

}




@end
