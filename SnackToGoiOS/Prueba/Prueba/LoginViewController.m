//
//  LoginViewController.m
//  Prueba
//
//  Created by Compean on 10/20/12.
//  Copyright (c) 2012 Compean. All rights reserved.
//

#import "LoginViewController.h"

@interface LoginViewController ()

@end

@implementation LoginViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
	// Do any additional setup after loading the view.
}

-(void)viewWillAppear:(BOOL)animated{
    [[self navigationController] setNavigationBarHidden:NO animated:YES];
    //self.navigationItem.backBarButtonItem = [[UIBarButtonItem alloc] initWithTitle:@"Back" style:UIBarButtonItemStyleBordered target:self action:@selector(goBack)];
                                             
}

/*-(void)goBack
{
        UIViewController *ctrl = [self.navigationController.viewControllers objectAtIndex:self.navigationController.viewControllers.count - 2];
        [self.navigationController popToViewController:ctrl animated:YES];
}*/
                                             
-(void)viewWillDisappear:(BOOL)animated{
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)goBack:(UIBarButtonItem *)sender {
    [self dismissViewControllerAnimated:YES completion:nil];
}
- (IBAction)quitarTeclado:(id)sender {
    [_usuario resignFirstResponder];
    [_contrasena resignFirstResponder];
}
@end
