//
//  MenuController.h
//  Prueba
//
//  Created by Compean on 10/21/12.
//  Copyright (c) 2012 Compean. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MenuController : UIViewController <UITableViewDataSource, UITableViewDelegate>

@property (strong, nonatomic) NSMutableArray *jsonArray;
@property (strong, nonatomic) IBOutlet UITableView *tablaMenu;
@property (weak, nonatomic) IBOutlet UITextField *noItem;
- (IBAction)actualizaCantidadPedida:(UIStepper *)sender;
@property (weak, nonatomic) IBOutlet UILabel *nombreProd;
@property (weak, nonatomic) IBOutlet UILabel *descripcion;
@property (weak, nonatomic) IBOutlet UIStepper *stepper;


@end
