//
//  MenuController.m
//  Prueba
//
//  Created by Compean on 10/21/12.
//  Copyright (c) 2012 Compean. All rights reserved.
//

#import "MenuController.h"

@interface MenuController ()

@end

@implementation MenuController

double total;

- (void)viewDidLoad
{
    [super viewDidLoad];
    for (int i =0; i < _jsonArray.count; i++) {
        NSMutableDictionary *jobj = [_jsonArray objectAtIndex:i];
        [jobj setObject:@"0" forKey:@"cantidad"];
    }
    // Uncomment the following line to preserve selection between presentations.
    // self.clearsSelectionOnViewWillAppear = NO;
 
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source
#pragma mark - Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    // Return the number of sections.
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    // Return the number of rows in the section.
    return _jsonArray.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    NSString *CellIdentifier = @"MyID";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier forIndexPath:indexPath];
    //UITableViewCell *cell;
    if (cell==nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleValue1 reuseIdentifier:CellIdentifier];
    }
    
    NSDictionary *jobj = [_jsonArray objectAtIndex:indexPath.row];
    
    cell.textLabel.text = [jobj objectForKey:@"nombre"];
    cell.detailTextLabel.text = [((NSNumber *)[jobj objectForKey:@"precio"]) stringValue];
    // Configure the cell...
    
    return cell;
}
/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/

/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath
{
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:@[indexPath] withRowAnimation:UITableViewRowAnimationFade];
    }   
    else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/

/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath
{
}
*/

/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/

#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    // Navigation logic may go here. Create and push another view controller.
    /*
     <#DetailViewController#> *detailViewController = [[<#DetailViewController#> alloc] initWithNibName:@"<#Nib name#>" bundle:nil];
     // ...
     // Pass the selected object to the new view controller.
     [self.navigationController pushViewController:detailViewController animated:YES];
     */
    NSMutableDictionary *jobj = [_jsonArray objectAtIndex:indexPath.row];
    
    _nombreProd.text=[jobj objectForKey:@"nombre"];
    _noItem.text=[jobj objectForKey:@"cantidad"];
    _stepper.value=[_noItem.text doubleValue];
    _descripcion.text=[jobj objectForKey:@"descripcion"];
    
}

- (void)viewWillDisappear:(BOOL)animated {
    NSArray *viewControllers = self.navigationController.viewControllers;
    if (viewControllers.count > 1 && [viewControllers objectAtIndex:viewControllers.count-2] == self) {
        // View is disappearing because a new view controller was pushed onto the stack
        NSLog(@"New view controller was pushed");
    } else if ([viewControllers indexOfObject:self] == NSNotFound) {
        // View is disappearing because it was popped from the stack
        NSLog(@"View controller was popped");
        //UIViewController *vc = [viewControllers objectAtIndex:viewControllers.count-2];
        //[[self navigationController] popToViewController:vc animated:YES];
        [[self navigationController] popViewControllerAnimated:YES];
    }
}


- (IBAction)actualizaCantidadPedida:(UIStepper *)sender {
     NSMutableDictionary *dict=[_jsonArray objectAtIndex:[[_tablaMenu indexPathForSelectedRow] row]];
    if ([[dict objectForKey:@"cantidad"] doubleValue]>sender.value) {
        total-=[((NSNumber *)[dict objectForKey:@"precio"]) doubleValue];
    } else {
        total+=[((NSNumber *)[dict objectForKey:@"precio"]) doubleValue];
    }
    _noItem.text=[[NSNumber numberWithDouble:sender.value] stringValue];
    _totalAPagar.text=[[NSNumber numberWithDouble:total] stringValue];
    [dict setObject:_noItem.text forKey:@"cantidad"];
}
@end
