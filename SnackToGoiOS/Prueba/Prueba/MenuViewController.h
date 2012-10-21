//
//  MenuViewController.h
//  Prueba
//
//  Created by Compean on 10/20/12.
//  Copyright (c) 2012 Compean. All rights reserved.
//

@class GCDAsyncSocket;

#import <UIKit/UIKit.h>

@interface MenuViewController : UITableViewController <UITableViewDelegate, UITableViewDataSource>

@property (strong, nonatomic) IBOutlet UITableView *tablaMenu;
@property (strong, nonatomic) NSMutableArray *jsonArray;

@end
