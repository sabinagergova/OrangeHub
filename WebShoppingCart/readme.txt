Usage and testing instructions
------------------------------
Installation:

1. Build from maven
2. Deploy to JBoss
3. The application uses a folder to store session data. The folder is automatically created in the root directory.
For windows it's C:\ and it works. For Unix it won't work so the PATH constant in FileManager class must be changed 
to a desired folder where the JVM will have read and write permissions.

Testing:
1. Input the following URL in the browser {host}:{port}/WebShoppingCart/add?itemName={product to add in the cart}
2. view shopping cart -  {host}:{port}/WebShoppingCart/view
3. remove an item from the shopping cart - {host}:{port}/WebShoppingCart/remove?itemName={product to remove from the cart}