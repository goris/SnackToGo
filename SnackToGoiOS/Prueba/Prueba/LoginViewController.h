//
//  LoginViewController.h
//  Prueba
//
//  Created by Compean on 10/20/12.
//  Copyright (c) 2012 Compean. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface LoginViewController : UIViewController

- (IBAction)goBack:(UIBarButtonItem *)sender;

@property (weak, nonatomic) IBOutlet UITextField *usuario;
@property (weak, nonatomic) IBOutlet UITextField *contrasena;
- (IBAction)quitarTeclado:(id)sender;

@end
