package com.persistance.mobileproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {
    private Button menuButton;
    private Button captureImageButton;
    int menuItemId1;
    int menuItemId2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        menuButton = findViewById(R.id.menuButton);
        captureImageButton = findViewById(R.id.captureImageButton);
        menuItemId1 = getResources().getIdentifier("menu_item_1", "id", getPackageName());
        menuItemId2 = getResources().getIdentifier("menu_item_2", "id", getPackageName());

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(MenuActivity.this, menuButton);
                MenuInflater inflater = popupMenu.getMenuInflater();
                inflater.inflate(R.menu.popup_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == menuItemId1) {
                            Toast.makeText(MenuActivity.this, "Menu Item 1 Clicked", Toast.LENGTH_SHORT).show();
                            return true;
                        } else if (item.getItemId() == menuItemId2) {
                            Intent menuIntent = new Intent(MenuActivity.this, MapsActivity.class);
                            startActivity(menuIntent);


                            return true;
                        } else {
                            return false;
                        }
                    }
                });

                popupMenu.show();
            }
        });

        captureImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open a file picker to allow the user to select an image from the device storage
                Intent pickImageIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickImageIntent.setType("image/*");
                startActivityForResult(pickImageIntent, 2);
            }
        });
        captureImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imagePath = "/Users/younes/Downloads/archive/PetImages/Dog/cccc";

                try {
                    // Check if the image file is opened successfully
                    ExifInterface exifInterface = new ExifInterface(imagePath);
                    if (exifInterface != null) {
                        // Image file opened successfully
                        // Read metadata from the ExifInterface object
                        // Example: Get location data, camera settings, etc.

                        // Example: Read and display the image width and height
                        int imageWidth = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0);
                        int imageHeight = exifInterface.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, 0);

                        // Example: Log the image width and height
                        Log.d("Image Metadata", "Image Width: " + imageWidth + ", Image Height: " + imageHeight);

                        // You can read other metadata tags as needed
                    } else {
                        // Image file could not be opened
                        Toast.makeText(MenuActivity.this, "Error opening the image file", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(MenuActivity.this, "Error reading image metadata", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
