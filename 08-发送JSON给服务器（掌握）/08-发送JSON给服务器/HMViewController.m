//
//  HMViewController.m
//  08-发送JSON给服务器
//
//  Created by apple on 14-9-19.
//  Copyright (c) 2014年 heima. All rights reserved.
//

#import "HMViewController.h"
#import "MBProgressHUD+MJ.h"

@interface HMViewController ()

@end

@implementation HMViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
}

- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event
{
    // 1.URL
    NSURL *url = [NSURL URLWithString:@"http://localhost:8080/MJServer/order"];
    
    // 2.请求
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:url];
    
    // 3.请求方法
    request.HTTPMethod = @"POST";
    
    // 4.设置请求体（请求参数）
    // 创建一个描述订单信息的JSON数据
    NSDictionary *orderInfo = @{
                                @"shop_id" : @"1243324",
                                @"shop_name" : @"啊哈哈哈",
                                @"user_id" : @"899",
                                @"isRegister" : @(YES),
                                @"ishaha" : @(NO),
                                @"nimabi" : @(1),
                                @"nijibi" : @(0)
                                };
    NSData *json = [NSJSONSerialization dataWithJSONObject:orderInfo options:NSJSONWritingPrettyPrinted error:nil];
    NSString * content = [[NSString alloc] initWithData:json encoding:NSUTF8StringEncoding];
    
    NSString * ioio = @"{  \"ishaha\" : false,  \"nimabi\" : 1,  \"nijibi\" : 0,  \"isRegister\" : true,  \"user_id\" : \"899\",  \"shop_id\" : \"1243324\",  \"shop_name\" : \"啊哈哈哈\"}";
    
    NSData *ioioData = [ioio dataUsingEncoding:NSUTF8StringEncoding];
     NSString * ioio2 =[[NSString alloc] initWithData:ioioData encoding:NSUTF8StringEncoding];
    request.HTTPBody = json;
//    request.HTTPBody = [ioio dataUsingEncoding:NSUTF8StringEncoding];
    
    // 5.设置请求头：这次请求体的数据不再是普通的参数，而是一个JSON数据
    [request setValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    
    // 6.发送请求
    [NSURLConnection sendAsynchronousRequest:request queue:[NSOperationQueue mainQueue] completionHandler:^(NSURLResponse *response, NSData *data, NSError *connectionError) {
        if (data == nil || connectionError) return;
        NSDictionary *dict = [NSJSONSerialization JSONObjectWithData:data options:NSJSONReadingMutableLeaves error:nil];
        NSString *error = dict[@"error"];
        if (error) {
            [MBProgressHUD showError:error];
        } else {
            NSString *success = dict[@"success"];
            [MBProgressHUD showSuccess:success];
        }
    }];
    
}

@end
